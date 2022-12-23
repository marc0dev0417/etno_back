package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.EventDTO
import com.etno.microservice.model.dto.PharmacyDTO
import com.etno.microservice.model.dto.TourismDTO
import com.etno.microservice.model.dto.UserDTO
import com.etno.microservice.repository.EventRepository
import com.etno.microservice.repository.ImageRepository
import com.etno.microservice.repository.PharmacyRepository
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
    private val imageRepository: ImageRepository,
    private val pharmacyRepository: PharmacyRepository,
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
                    UsernamePasswordAuthenticationToken(
                        username,
                        password
                    )
                )
            if (auth.isAuthenticated) {
                val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
                val token: String = jwtTokenUtil.generateToken(userDetails) ?: ""
                responseMap["error"] = false
                responseMap["message"] = "Logged In"
                responseMap["token"] = token
                responseMap["token_expired"] = jwtTokenUtil.isTokenExpired(token).toString()
                responseMap["expired_date"] =
                    dataFormatMedium.format(jwtTokenUtil.getExpirationDateFromToken(token)).toString()
                responseMap["user"] = DataConverter.userToDTO(user!!)

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

    override fun updateUserCredentials(name: String, username: String, password: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(name)
        itemUser?.username = username
        itemUser?.password = BCryptPasswordEncoder().encode(password)
        val itemUserSaved = userRepository.save(itemUser!!)

        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun addEventInUser(username: String, eventDTO: EventDTO): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemEvent = eventRepository.findEventByTitleAndUsername(eventDTO.title!!, username)

        if(itemEvent == null){
            eventDTO.username = itemUser?.username
            val itemEventSaved = eventRepository.save(DataConverter.eventFromDTO(eventDTO))
            itemUser?.events?.add(DataConverter.eventFromDTO(DataConverter.eventToDTO(itemEventSaved)))
            userRepository.save(itemUser!!)
        }else{
            val checkIfExistEvent = itemUser?.events?.find { it.title == itemEvent.title }
            if(checkIfExistEvent == null){
                eventDTO.username = itemUser?.username
                itemUser?.events?.add(DataConverter.eventFromDTO(eventDTO))
                userRepository.save(itemUser!!)
            }
        }
        return DataConverter.userToDTO(itemUser)
    }
    override fun deleteEventInUser(username: String, title: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemEvent = eventRepository.findEventByTitleAndUsername(title, username)

        itemUser?.events?.remove(itemEvent!!)
        val itemSaved = userRepository.save(itemUser!!)
        eventRepository.delete(itemEvent!!)
        return DataConverter.userToDTO(itemSaved)
    }

    override fun addImageToEventInUser(username: String, title: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemEvent = eventRepository.findEventByTitleAndUsername(title, username)
        val itemImage = imageRepository.findImageByName(imageName)

       // eventRepository.save(itemEvent!!)
        itemUser?.events?.find { it.title == title }?.images?.add(itemImage)

       val itemUserSaved = userRepository.save(itemUser!!)

        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun deleteImageToEventInUser(username: String, title: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemEvent = eventRepository.findEventByTitleAndUsername(title, username)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.events?.find{ it.title == itemEvent?.title }?.images?.remove(itemImage)
        val itemUserSaved = userRepository.save(itemUser!!)

        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun addPharmacyInUser(username: String, pharmacyDTO: PharmacyDTO): UserDTO? {

        val itemUser = userRepository.findUserByUsername(username)
        val itemPharmacy = pharmacyRepository.findPharmacyByNameAndUsername(pharmacyDTO.name!!, username)

        if(itemPharmacy == null){
            pharmacyDTO.idPharmacy = UUID.randomUUID()
            pharmacyDTO.username = itemUser?.username
            itemUser?.pharmacies?.add(DataConverter.pharmacyFromDTO(pharmacyDTO))
            userRepository.save(itemUser!!)
        }else{
            val checkIfExistPharmacy = itemUser?.pharmacies?.find { it.name == itemPharmacy.name }
            if(checkIfExistPharmacy == null){
                pharmacyDTO.idPharmacy = UUID.randomUUID()
                pharmacyDTO.username = itemUser?.username
                itemUser?.pharmacies?.add(DataConverter.pharmacyFromDTO(pharmacyDTO))
                userRepository.save(itemUser!!)
            }
        }
        return DataConverter.userToDTO(itemUser)
    }

    override fun deletePharmacyInUser(username: String, name: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemPharmacy = pharmacyRepository.findPharmacyByNameAndUsername(name, username)

        itemUser?.pharmacies?.remove(itemPharmacy!!)
        val itemSaved = userRepository.save(itemUser!!)
        pharmacyRepository.delete(itemPharmacy!!)
        return DataConverter.userToDTO(itemSaved)
    }

    override fun addImageToPharmacyInUser(username: String, name: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemPharmacy = pharmacyRepository.findPharmacyByNameAndUsername(name, username)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.pharmacies?.find { it.name == itemPharmacy?.name }?.imageUrl = itemImage.link
        val itemUserSaved = userRepository.save(itemUser!!)

        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun deleteImageToPharmacyInUser(username: String, name: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemPharmacy = pharmacyRepository.findPharmacyByNameAndUsername(name, username)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.pharmacies?.find { it.name == itemPharmacy?.name }?.imageUrl = ""
        val itemUserSaved = userRepository.save(itemUser!!)

        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun addTourismInUser(username: String, tourismDTO: TourismDTO): UserDTO? {
        TODO("NOT yet implemented")
    }

    override fun deleteTourismInUser(username: String, title: String, imageName: String): UserDTO? {
        TODO("Not yet implemented")
    }

    override fun addImageToTourismInUser(username: String, title: String, imageName: String): UserDTO? {
        TODO("Not yet implemented")
    }

    override fun deleteImageToTourismInUser(username: String, title: String, imageName: String): UserDTO? {
        TODO("Not yet implemented")
    }
}