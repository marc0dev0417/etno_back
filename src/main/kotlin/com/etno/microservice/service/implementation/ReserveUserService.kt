package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.ReserveUserDTO
import com.etno.microservice.repository.ReserveUserRepository
import com.etno.microservice.service.ReserveUserServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service

@Service
class ReserveUserService(
    private val reserveUserRepository: ReserveUserRepository
): ReserveUserServiceInterface{
    override fun getReserveUsersByFcmToken(fcmToken: String): List<ReserveUserDTO> {
        return reserveUserRepository.getReserveUsersByFcmToken(fcmToken)!!.map { DataConverter.reserveUserToDTO(it) }
    }
}