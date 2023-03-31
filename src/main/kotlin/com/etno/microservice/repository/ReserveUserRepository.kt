package com.etno.microservice.repository

import com.etno.microservice.model.ReserveUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ReserveUserRepository: JpaRepository<ReserveUser, UUID> {
    fun getReserveUsersByFcmToken(fcmToken: String): List<ReserveUser>?
}