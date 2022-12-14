package com.etno.microservice.service.implementation

import com.etno.microservice.model.Subscription
import com.etno.microservice.model.dto.FCMTokenDTO
import com.etno.microservice.model.dto.SectionDTO
import com.etno.microservice.repository.FCMTokenRepository
import com.etno.microservice.repository.SubscriptionRepository
import com.etno.microservice.service.FCMTokenServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service
import java.util.*

@Service
class FCMTokenService(
    private val fcmTokenRepository: FCMTokenRepository,
    private val subscriptionRepository: SubscriptionRepository
): FCMTokenServiceInterface {
    override fun getFCMTokens(): List<FCMTokenDTO>? {
        return fcmTokenRepository.findAll().map { DataConverter.fcmTokenToDTO(it) }
    }

    override fun saveFCMToken(fcmTokenDTO: FCMTokenDTO): FCMTokenDTO? {
        val itemToSave = DataConverter.fcmTokenFromDTO(fcmTokenDTO)
        itemToSave.idFMC = UUID.randomUUID()
        val itemSaved = fcmTokenRepository.save(itemToSave)
        return DataConverter.fcmTokenToDTO(itemSaved)
    }


    override fun addSectionToFcmToken(token: String, sectionDTO: SectionDTO): FCMTokenDTO? {
        val itemFound = fcmTokenRepository.findFCMTokenByToken(token)
        val itemSubscription = subscriptionRepository.findSubscriptionByTokenAndTitle(token, sectionDTO.title!!)

        if(itemSubscription != null){
            itemSubscription.token = itemFound.token
            itemSubscription.title = sectionDTO.title
            itemSubscription.category = sectionDTO.category
            itemSubscription.isSubscribe = true
            subscriptionRepository.save(itemSubscription)
        }else{
            subscriptionRepository.save(Subscription(token = itemFound.token, category = sectionDTO.category, title = sectionDTO.title, isSubscribe = true))
        }
        return DataConverter.fcmTokenToDTO(itemFound)
    }

    override fun dropOutSectionByTokenAndTitle(token: String, sectionTitle: String): FCMTokenDTO? {
        val itemFcmToken = fcmTokenRepository.findFCMTokenByToken(token)
        val itemSubscription = subscriptionRepository.findSubscriptionByTokenAndTitle(token, sectionTitle)
        itemSubscription!!.isSubscribe = false
        subscriptionRepository.save(itemSubscription)
        return DataConverter.fcmTokenToDTO(itemFcmToken)
    }

}