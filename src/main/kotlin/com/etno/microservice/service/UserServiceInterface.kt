package com.etno.microservice.service

import com.etno.microservice.model.dto.UserDTO

interface UserServiceInterface {
    fun getUsers(): List<UserDTO>?
    fun saveUser(userDTO: UserDTO): UserDTO?
}