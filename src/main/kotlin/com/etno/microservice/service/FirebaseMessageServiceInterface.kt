package com.etno.microservice.service

import com.etno.microservice.model.dto.firebase.NoteDTO
import com.etno.microservice.model.dto.firebase.NotificationDTO
import org.springframework.stereotype.Service

@Service
interface FirebaseMessageServiceInterface {
    fun sendNotification(note: NoteDTO): NotificationDTO?
}