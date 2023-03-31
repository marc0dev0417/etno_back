package com.etno.microservice.service

import com.etno.microservice.model.dto.ReserveUserDTO
import org.springframework.stereotype.Service

@Service
interface ReserveUserServiceInterface {
    fun getReserveUsersByFcmToken(fcmToken: String): List<ReserveUserDTO>
}