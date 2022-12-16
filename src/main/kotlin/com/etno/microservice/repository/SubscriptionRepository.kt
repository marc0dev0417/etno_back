package com.etno.microservice.repository

import com.etno.microservice.model.Subscription
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface SubscriptionRepository: JpaRepository<Subscription,UUID> {
        fun findSubscriptionByCategoryAndTitle(category: String, title: String): Subscription?

}