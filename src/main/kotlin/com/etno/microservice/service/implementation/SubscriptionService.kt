package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.SubscriptionDTO
import com.etno.microservice.model.dto.SubscriptionUserDTO
import com.etno.microservice.repository.SubscriptionRepository
import com.etno.microservice.service.SubscriptionServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service
import java.util.*

@Service
class SubscriptionService(
    private val subscriptionRepository: SubscriptionRepository
): SubscriptionServiceInterface {
    override fun getSubscriptions(): List<SubscriptionDTO>? {
        return subscriptionRepository.findAll().map { DataConverter.subscriptionToDTO(it) }
    }


    override fun getSubscription(token: String, category: String, title: String): SubscriptionUserDTO? {
       val itemSubscription = subscriptionRepository.findSubscriptionByCategoryAndTitle( category = category, title = title)
        val itemSubscriptionUserFound = itemSubscription?.subscriptionsUsers?.find { subscriptionUser -> subscriptionUser.fcmToken == token }

        return DataConverter.subscriptionUserToDTO(itemSubscriptionUserFound!!)
    }

    override fun saveSubscription(subscriptionDTO: SubscriptionDTO): SubscriptionDTO? {
        val itemToSave = DataConverter.subscriptionFromDTO(subscriptionDTO)
        itemToSave.idSectionSubscribe = UUID.randomUUID()
        val itemSaved = subscriptionRepository.save(itemToSave)
        return DataConverter.subscriptionToDTO(itemSaved)
    }
}