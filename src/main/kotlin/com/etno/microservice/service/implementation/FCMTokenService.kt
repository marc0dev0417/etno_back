package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.FCMTokenDTO
import com.etno.microservice.repository.FCMTokenRepository
import com.etno.microservice.service.FCMTokenServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service
import java.util.*

@Service
class FCMTokenService(
    private val fcmTokenRepository: FCMTokenRepository
): FCMTokenServiceInterface {
    override fun getFCMTokens(): List<FCMTokenDTO>? {
        return fcmTokenRepository.findAll().map { DataConverter.fcmTokenToDTO(it) }
    }

    override fun saveFCMToken(fcmTokenDTO: FCMTokenDTO): FCMTokenDTO? {
        val itemToSave = DataConverter.fcmTokenFromDTO(fcmTokenDTO)
        itemToSave.idFMC = UUID.randomUUID()
        val itemSaved = fcmTokenRepository.save(itemToSave)

        return DataConverter.fcmTokenToDTO(itemSaved)
    }
}