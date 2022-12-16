package com.etno.microservice.service

import com.etno.microservice.model.dto.SubscriptionDTO
import com.etno.microservice.model.dto.SubscriptionUserDTO
import org.springframework.stereotype.Service

@Service
interface SubscriptionServiceInterface {
    fun getSubscriptions(): List<SubscriptionDTO>?
    fun getSubscription(token: String, category: String, title: String): SubscriptionUserDTO?
    fun saveSubscription(subscriptionDTO: SubscriptionDTO): SubscriptionDTO?
}