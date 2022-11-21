package com.etno.microservice.repository

import com.etno.microservice.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository: JpaRepository<User, UUID> {

}