package com.etno.microservice.controller.implementation.firebase

import com.etno.microservice.controller.FirebaseCloudMessageControllerInterface
import com.etno.microservice.model.dto.firebase.NoteDTO
import com.etno.microservice.model.dto.firebase.NotificationDTO
import com.etno.microservice.service.implementation.firebase.FirebaseCloudMessageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Firebase(
    private val firebaseCloudMessageService: FirebaseCloudMessageService
): FirebaseCloudMessageControllerInterface {
    override fun sendNotification(@RequestBody note: NoteDTO): ResponseEntity<NotificationDTO>? {
        return ResponseEntity.ok().body(firebaseCloudMessageService.sendNotification(note))
    }
}