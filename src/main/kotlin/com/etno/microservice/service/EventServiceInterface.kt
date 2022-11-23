package com.etno.microservice.service

import com.etno.microservice.model.dto.EventDTO
import org.springframework.stereotype.Service

@Service
interface EventServiceInterface {
    fun getEvents(): List<EventDTO>?
    fun saveEvents(eventDTO: EventDTO): EventDTO?
}