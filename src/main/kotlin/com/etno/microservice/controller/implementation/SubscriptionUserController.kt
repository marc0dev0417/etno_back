package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.SubscriptionUserControllerInterface
import com.etno.microservice.model.dto.SubscriptionUserDTO
import com.etno.microservice.service.implementation.SubscriptionUserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class SubscriptionUserController(
    private val subscriptionUserService: SubscriptionUserService
): SubscriptionUserControllerInterface {
    override fun getSubscriptionUsers(): ResponseEntity<List<SubscriptionUserDTO>> {
        return ResponseEntity.ok().body(subscriptionUserService.getSubscriptionUsers())
    }

    override fun getSubscription(fcmToken: String, title: String): ResponseEntity<SubscriptionUserDTO> {
        return ResponseEntity.ok().body(subscriptionUserService.getSubscription(fcmToken, title))
    }

    override fun saveSubscriptionUser(subscriptionUserDTO: SubscriptionUserDTO): ResponseEntity<SubscriptionUserDTO> {
        return ResponseEntity.ok().body(subscriptionUserService.saveSubscriptionUser(subscriptionUserDTO))
    }
}