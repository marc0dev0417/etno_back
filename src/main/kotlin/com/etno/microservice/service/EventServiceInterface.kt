package com.etno.microservice.service

import com.etno.microservice.model.dto.EventDTO
import org.springframework.stereotype.Service

@Service
interface EventServiceInterface {
    fun getEvents(): List<EventDTO>?
    fun saveEvents(eventDTO: EventDTO): EventDTO?
    fun deleteEventByTitle(title: String): EventDTO?
    fun addImageToEvent(title: String, imageName: String): EventDTO?
    fun deleteImageToEvent(title: String, imageName: String): EventDTO?
}