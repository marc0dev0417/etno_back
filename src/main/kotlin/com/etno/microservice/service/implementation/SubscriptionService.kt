package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.SubscriptionDTO
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

    override fun getSubscription(token: String, category: String, title: String): SubscriptionDTO? {
        return DataConverter.subscriptionToDTO(subscriptionRepository.findSubscriptionByTokenAndCategoryAndTitle(token = token, category = category, title = title)!!)
    }

    override fun saveSubscription(subscriptionDTO: SubscriptionDTO): SubscriptionDTO? {
        val itemToSave = DataConverter.subscriptionFromDTO(subscriptionDTO)
        itemToSave.idSectionSubscribe = UUID.randomUUID()
        val itemSaved = subscriptionRepository.save(itemToSave)
        return DataConverter.subscriptionToDTO(itemSaved)
    }
}