package com.etno.microservice.service

import com.etno.microservice.model.dto.FCMTokenDTO
import com.etno.microservice.model.dto.SectionDTO
import org.springframework.stereotype.Service

@Service
interface FCMTokenServiceInterface {
    fun getFCMTokens(): List<FCMTokenDTO>?
    fun saveFCMToken(fcmTokenDTO: FCMTokenDTO): FCMTokenDTO?

}