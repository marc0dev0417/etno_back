package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.SubscriptionControllerInterface
import com.etno.microservice.model.dto.SubscriptionDTO
import com.etno.microservice.model.dto.SubscriptionUserDTO
import com.etno.microservice.service.implementation.SubscriptionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class SubscriptionController(
    private val subscriptionService: SubscriptionService
): SubscriptionControllerInterface {
    override fun getSubscriptions(): ResponseEntity<List<SubscriptionDTO>> {
        return ResponseEntity.ok().body(subscriptionService.getSubscriptions())
    }

    override fun saveSubscription(subscriptionDTO: SubscriptionDTO): ResponseEntity<SubscriptionDTO> {
        return ResponseEntity.ok().body(subscriptionService.saveSubscription(subscriptionDTO))
    }

    override fun findSubscriptionCategoryAndTitleAndUser(
        token: String,
        category: String,
        title: String
    ): ResponseEntity<SubscriptionUserDTO> {
        return ResponseEntity.ok().body(subscriptionService.getSubscription(token = token, category = category, title = title))
    }
}