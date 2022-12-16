package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.SubscriptionUserDTO
import com.etno.microservice.repository.SubscriptionUserRepository
import com.etno.microservice.service.SubscriptionUserServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service
import java.util.*

@Service
class SubscriptionUserService(
    private val subscriptionUserRepository: SubscriptionUserRepository
): SubscriptionUserServiceInterface {
    override fun getSubscriptionUsers(): List<SubscriptionUserDTO>? {
        return subscriptionUserRepository.findAll().map { DataConverter.subscriptionUserToDTO(it) }
    }

    override fun saveSubscriptionUser(subscriptionUserDTO: SubscriptionUserDTO): SubscriptionUserDTO? {
        val itemToSave = DataConverter.subscriptionUserFromDTO(subscriptionUserDTO)
        itemToSave.idSubscriptionUser = UUID.randomUUID()
        val itemSaved = subscriptionUserRepository.save(itemToSave)
        return DataConverter.subscriptionUserToDTO(itemSaved)
    }
}