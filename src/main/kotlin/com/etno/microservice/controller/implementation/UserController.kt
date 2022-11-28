package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.UserControllerInterface
import com.etno.microservice.model.dto.UserDTO
import com.etno.microservice.service.implementation.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
):UserControllerInterface {
    override fun getUsers(): ResponseEntity<List<UserDTO>>? {
        return ResponseEntity.ok().body(userService.getUsers())
    }

    override fun saveUser(userDTO: UserDTO): ResponseEntity<UserDTO>? {
        return ResponseEntity.ok().body(userService.signUp(userDTO))
    }

    override fun login(username: String, password: String): ResponseEntity<*>? {
        return userService.login(username, password)
    }

    override fun updateUser(username: String, userDTO: UserDTO): ResponseEntity<UserDTO>? {
        return ResponseEntity.ok().body(userService.updateUser(username, userDTO))
    }


}