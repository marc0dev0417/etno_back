package com.etno.microservice.controller.implementation.firebase

import com.etno.microservice.controller.FCMTokenControllerInterface
import com.etno.microservice.model.dto.FCMTokenDTO
import com.etno.microservice.model.dto.SectionDTO
import com.etno.microservice.service.implementation.FCMTokenService
import org.springframework.http.ResponseEntity
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

    override fun addSectionToFcmToken(token: String, sectionDTO: SectionDTO): ResponseEntity<FCMTokenDTO>? {
        return ResponseEntity.ok().body(fcmTokenService.addSectionToFcmToken(token, sectionDTO))
    }

    override fun dropOutSectionByTokenAndTitle(token: String, title: String): ResponseEntity<FCMTokenDTO> {
        return ResponseEntity.ok().body(fcmTokenService.dropOutSectionByTokenAndTitle(token, title))
    }
}