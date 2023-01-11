package com.etno.microservice.service

import com.etno.microservice.model.dto.FCMTokenDTO
import com.etno.microservice.model.dto.SectionDTO
import com.etno.microservice.model.dto.SubscriptionDTO
import org.springframework.stereotype.Service

@Service
interface FCMTokenServiceInterface {
    fun getFCMTokens(): List<FCMTokenDTO>?
    fun saveFCMToken(fcmTokenDTO: FCMTokenDTO): FCMTokenDTO?
    fun addSectionToFcmToken(
        token: String,
        nameUser: String,
        mail: String,
        phone: String,
        wallet: Double,
        sectionDTO: SectionDTO
    ): SubscriptionDTO?
    fun dropOutSectionByTokenAndTitle(token: String, username: String ,category: String, sectionTitle: String): SubscriptionDTO?
}