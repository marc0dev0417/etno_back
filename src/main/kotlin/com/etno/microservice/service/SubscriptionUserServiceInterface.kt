package com.etno.microservice.service

import com.etno.microservice.model.SubscriptionUser
import com.etno.microservice.model.dto.SubscriptionUserDTO
import org.springframework.stereotype.Service

@Service
interface SubscriptionUserServiceInterface {
    fun getSubscriptionUsers(): List<SubscriptionUserDTO>?
    fun saveSubscriptionUser(subscriptionUserDTO: SubscriptionUserDTO): SubscriptionUserDTO?
}