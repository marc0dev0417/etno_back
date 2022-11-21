package com.etno.microservice.util

import com.etno.microservice.model.User
import com.etno.microservice.model.dto.UserDTO

object DataConverter {
    fun userToDTO(user: User): UserDTO{
        return UserDTO(
            id = user.id,
            username = user.username,
            password = user.password,
            role = user.role
        )
    }
    fun userFromDto(userDTO: UserDTO): User{
        return User(
            id = userDTO.id,
            username = userDTO.username,
            password = userDTO.password,
            role = userDTO.role
        )
    }
}