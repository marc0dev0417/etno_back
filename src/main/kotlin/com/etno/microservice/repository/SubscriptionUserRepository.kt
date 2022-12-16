package com.etno.microservice.repository

import com.etno.microservice.model.SubscriptionUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface SubscriptionUserRepository: JpaRepository<SubscriptionUser, UUID> {
    fun findSubscriptionUserByName(name: String): SubscriptionUser?
    fun findSubscriptionUserByFcmToken(token: String): SubscriptionUser?
}