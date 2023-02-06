package com.etno.microservice.service

import com.etno.microservice.model.dto.EventDTO
import com.etno.microservice.model.dto.pagination.EventPageDTO
import org.springframework.stereotype.Service

@Service
interface EventServiceInterface {
    fun getEvents(): List<EventDTO>?
    fun findEventByTitleAndUsername(title: String, username: String): EventDTO?
    fun findEventsByUsername(username: String): List<EventDTO>?
    fun saveEvents(eventDTO: EventDTO): EventDTO?
    fun deleteEventByTitleAndUsername(title: String, username: String): EventDTO?
    fun addImageToEvent(username: String, title: String, imageName: String): EventDTO?
    fun deleteImageToEvent(username: String, title: String, imageName: String): EventDTO?
    fun getEventsPaginated(username: String, pageNum: Int, pageSize: Int): EventPageDTO?
}