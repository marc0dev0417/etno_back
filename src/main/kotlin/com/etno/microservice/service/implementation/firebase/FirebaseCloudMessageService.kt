package com.etno.microservice.service.implementation.firebase

import com.etno.microservice.model.dto.firebase.NoteDTO
import com.etno.microservice.model.dto.firebase.NotificationDTO
import com.etno.microservice.repository.FCMTokenRepository
import com.etno.microservice.service.FirebaseMessageServiceInterface
import com.etno.microservice.util.firebaseMessaging
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Service
import java.util.*

@Service
class FirebaseCloudMessageService(
    private val fcmTokenRepository: FCMTokenRepository
): FirebaseMessageServiceInterface {

   override fun sendNotification(note: NoteDTO): NotificationDTO? {

        //val listFmc = fcmTokenRepository.findAll().let { fcmTokenRepository.findAll().map { it.token } }
        val listFmc = fcmTokenRepository.findAll().filter { it.username == note.username }.map { it.token }

        val message = MulticastMessage
            .builder()
            .setNotification(Notification.builder()
                .setTitle(note.subject)
                .setBody(note.content)
                .build())
            .putData("payload", "data1")
            .addAllTokens(listFmc)
            .build()

       firebaseMessaging().sendMulticast(message)

        return NotificationDTO(
            idNotification = UUID.randomUUID(),
            username = note.username,
            subject = note.subject,
            content = note.content,
            imageUrl = note.image
        )
    }
}