package com.etno.microservice.repository

import com.etno.microservice.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository: JpaRepository<User, Int> {
    fun findUserByUsername(username: String): User
}