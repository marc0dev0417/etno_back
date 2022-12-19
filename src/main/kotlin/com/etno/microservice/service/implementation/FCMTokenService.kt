package com.etno.microservice.service.implementation

import com.etno.microservice.model.Subscription
import com.etno.microservice.model.SubscriptionUser
import com.etno.microservice.model.dto.FCMTokenDTO
import com.etno.microservice.model.dto.SectionDTO
import com.etno.microservice.model.dto.SubscriptionDTO
import com.etno.microservice.repository.FCMTokenRepository
import com.etno.microservice.repository.SubscriptionRepository
import com.etno.microservice.repository.SubscriptionUserRepository
import com.etno.microservice.service.FCMTokenServiceInterface
import com.etno.microservice.util.DataConverter
import com.etno.microservice.util.Urls
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*

@Service
class FCMTokenService(
    private val fcmTokenRepository: FCMTokenRepository,
    private val subscriptionRepository: SubscriptionRepository,
    private val subscriptionUserRepository: SubscriptionUserRepository
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

    override fun addSectionToFcmToken(
        token: String,
        nameUser: String,
        mail: String,
        phone: String,
        wallet: Double,
        sectionDTO: SectionDTO
    ): SubscriptionDTO? {
        val itemFound = fcmTokenRepository.findFCMTokenByToken(token)
        var itemSubscription = subscriptionRepository.findSubscriptionByCategoryAndTitle(category = sectionDTO.category!!, sectionDTO.title!!)
        var itemSubscriptionUser = subscriptionUserRepository.findSubscriptionUserByFcmToken(token)

        if(itemSubscription != null && itemSubscriptionUser != null){
            itemSubscription.title = sectionDTO.title
            itemSubscription.category = sectionDTO.category
            itemSubscription.isSubscribe = true
            itemSubscription.subscriptionsUsers?.find { it.fcmToken == token }?.also {
                it.isSubscribe = true
                it.name = nameUser
                it.mail = mail
                it.phone = phone
                it.wallet = wallet
            }
            subscriptionRepository.save(itemSubscription)
        }else{
            itemSubscriptionUser = subscriptionUserRepository.save(SubscriptionUser(fcmToken = itemFound.token, name = nameUser, mail = mail, phone = phone, wallet = wallet, isSubscribe = true))
            itemSubscription =  subscriptionRepository.save(Subscription(category = sectionDTO.category, title = sectionDTO.title, isSubscribe = true, subscriptionsUsers = mutableListOf(itemSubscriptionUser)))
        }
        return DataConverter.subscriptionToDTO(itemSubscription)
    }

    override fun dropOutSectionByTokenAndTitle(
        token: String,
        category: String,
        sectionTitle: String
    ): SubscriptionDTO? {
        val itemSubscription = subscriptionRepository.findSubscriptionByCategoryAndTitle(category, sectionTitle)

       val itemSubscriptionUser = itemSubscription?.subscriptionsUsers?.find { subscriptionUser -> subscriptionUser.fcmToken == token }
        val subscriptionUser = subscriptionUserRepository.findSubscriptionUserByFcmToken(token)

        if(subscriptionUser != null){
            subscriptionUser.isSubscribe = false
            subscriptionUserRepository.save(subscriptionUser)
        }else{
            subscriptionUserRepository.save(SubscriptionUser(
                fcmToken = itemSubscriptionUser?.fcmToken,
                name = itemSubscriptionUser?.name,
                mail = itemSubscriptionUser?.mail,
                phone = itemSubscriptionUser?.phone,
                wallet = itemSubscriptionUser?.wallet,
                isSubscribe = false
            ))
        }
        itemSubscription!!.isSubscribe = false
        val itemSubscriptionSaved = subscriptionRepository.save(itemSubscription)
        return DataConverter.subscriptionToDTO(itemSubscriptionSaved)
    }
}