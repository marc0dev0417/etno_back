package com.etno.microservice.service

import com.etno.microservice.model.dto.UserDTO
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
interface UserServiceInterface {
    fun getUsers(): List<UserDTO>?
    fun SignUp(userDTO: UserDTO): UserDTO?
    fun login(username: String, password: String): ResponseEntity<*>

}