package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.UserDTO
import com.etno.microservice.repository.UserRepository
import com.etno.microservice.service.UserServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
): UserServiceInterface {

    override fun getUsers(): List<UserDTO>? {
        return userRepository.findAll().map { DataConverter.userToDTO(it) }
    }

    override fun saveUser(userDTO: UserDTO): UserDTO? {

        return if(!userRepository.existsById(userDTO.id)){
            val itemUser = userRepository.save(DataConverter.userFromDto(userDTO))
            DataConverter.userToDTO(itemUser)
        }else{
            null
        }
    }
}