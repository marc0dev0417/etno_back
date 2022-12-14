package com.etno.microservice.service

import com.etno.microservice.model.dto.SubscriptionDTO
import org.springframework.stereotype.Service

@Service
interface SubscriptionServiceInterface {
    fun getSubscriptions(): List<SubscriptionDTO>?
    fun getSubscription(token: String, category: String, title: String): SubscriptionDTO?
    fun saveSubscription(subscriptionDTO: SubscriptionDTO): SubscriptionDTO?
}