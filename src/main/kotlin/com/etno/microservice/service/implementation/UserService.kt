package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.UserDTO
import com.etno.microservice.repository.EventRepository
import com.etno.microservice.repository.UserRepository
import com.etno.microservice.security.JwtTokenUtil
import com.etno.microservice.service.UserServiceInterface
import com.etno.microservice.service.implementation.jwt.JwtUserDetailsService
import com.etno.microservice.util.DataConverter
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.text.DateFormat
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val eventRepository: EventRepository,
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: JwtUserDetailsService,
    private val jwtTokenUtil: JwtTokenUtil
): UserServiceInterface {

    override fun getUsers(): List<UserDTO>? {
        return userRepository.findAll().map { DataConverter.userToDTO(it) }
    }

    override fun signUp(userDTO: UserDTO): UserDTO? {
            val userItem = DataConverter.userFromDTO(userDTO)
            userItem.idUser = UUID.randomUUID()
            userItem.password = BCryptPasswordEncoder().encode(userItem.password)
            val userToSave = userRepository.save(userItem)
            return DataConverter.userToDTO(userToSave)

    }

    override fun login(username: String, password: String): ResponseEntity<*> {
        val responseMap: MutableMap<String, Any> = mutableMapOf()
        val user = userRepository.findUserByUsername(username)
        val dataFormatMedium = DateFormat.getDateInstance(DateFormat.MEDIUM)

        try {
            val auth: Authentication =
                authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(username,
                        password)
                )
            if (auth.isAuthenticated) {
                val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
                val token: String = jwtTokenUtil.generateToken(userDetails) ?: ""
                responseMap["error"] = false
                responseMap["message"] = "Logged In"
                responseMap["token"] = token
                responseMap["token_expired"] = jwtTokenUtil.isTokenExpired(token).toString()
                responseMap["expired_date"] = dataFormatMedium.format(jwtTokenUtil.getExpirationDateFromToken(token)).toString()
                responseMap["user"] = user

                return ResponseEntity.ok(responseMap)
            } else {
                responseMap["error"] = true
                responseMap["message"] = "Invalid Credentials"
                return ResponseEntity.status(401).body(responseMap)
            }

        } catch (e: DisabledException) {
            e.printStackTrace();
            responseMap["error"] = true
            responseMap["message"] = "User is disabled"
            return ResponseEntity.status(500).body(responseMap);
        } catch (e: BadCredentialsException) {
            responseMap["error"] = true
            responseMap["message"] = "Invalid Credentials"
            return ResponseEntity.status(401).body(responseMap);
        } catch (e: Exception) {
            e.printStackTrace();
            responseMap["error"] = true
            responseMap["message"] = "Something went wrong";
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    override fun addEventToUser(username: String, title: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemEvent = eventRepository.findByTitle(title)

        val itemUserDTO = userRepository.save(itemUser)

        return DataConverter.userToDTO(itemUserDTO)
    }
}