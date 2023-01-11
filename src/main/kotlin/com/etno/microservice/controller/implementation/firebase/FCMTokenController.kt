package com.etno.microservice.controller.implementation.firebase

import com.etno.microservice.controller.FCMTokenControllerInterface
import com.etno.microservice.model.dto.FCMTokenDTO
import com.etno.microservice.model.dto.SectionDTO
import com.etno.microservice.model.dto.SubscriptionDTO
import com.etno.microservice.service.implementation.FCMTokenService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FCMTokenController(
    private val fcmTokenService: FCMTokenService
): FCMTokenControllerInterface {
    override fun getFCMToken(): ResponseEntity<List<FCMTokenDTO>>? {
        return ResponseEntity.ok().body(fcmTokenService.getFCMTokens())
    }

    override fun saveFCMToken(fcmTokenDTO: FCMTokenDTO): ResponseEntity<FCMTokenDTO>? {
        return ResponseEntity.ok().body(fcmTokenService.saveFCMToken(fcmTokenDTO))
    }

    override fun addSectionToFcmToken(
        token: String,
        nameUser: String,
        mail: String,
        phone: String,
        wallet: Double,
        sectionDTO: SectionDTO
    ): ResponseEntity<SubscriptionDTO>? {
        return ResponseEntity.ok().body(fcmTokenService.addSectionToFcmToken(token = token, nameUser = nameUser, mail = mail, phone = phone, wallet = wallet, sectionDTO = sectionDTO))
    }

    override fun dropOutSectionByTokenAndTitle(
        token: String,
        username: String,
        category: String,
        title: String
    ): ResponseEntity<SubscriptionDTO> {
        return ResponseEntity.ok().body(fcmTokenService.dropOutSectionByTokenAndTitle(token = token, username = username, category = category, sectionTitle = title))
    }


}