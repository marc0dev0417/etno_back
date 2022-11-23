package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.EventDTO
import com.etno.microservice.repository.EventRepository
import com.etno.microservice.service.EventServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service
import java.util.*

@Service
class EventService(
    private val eventRepository: EventRepository
): EventServiceInterface {
    override fun getEvents(): List<EventDTO>? {
        return eventRepository.findAll().map { DataConverter.eventToDTO(it) }
    }

    override fun saveEvents(eventDTO: EventDTO): EventDTO? {
        val eventItem = DataConverter.eventFromDTO(eventDTO)
        eventItem.id = UUID.randomUUID()
        val eventToSave = eventRepository.save(eventItem)
        return DataConverter.eventToDTO(eventToSave)
    }
}