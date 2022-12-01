package com.etno.microservice.service.implementation

import com.etno.microservice.exception.Constants
import com.etno.microservice.exception.handler.ListEmptyException
import com.etno.microservice.model.dto.EventDTO
import com.etno.microservice.repository.EventRepository
import com.etno.microservice.repository.ImageRepository
import com.etno.microservice.service.EventServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class EventService(
    @Autowired
    private val eventRepository: EventRepository,

    @Autowired
    private val imageRepository: ImageRepository,

): EventServiceInterface {
    override fun getEvents(): List<EventDTO>? {
        if(eventRepository.findAll().isEmpty()){
            throw ListEmptyException(Constants.LIST_EMPTY.code, Constants.LIST_EMPTY)
        }

        return eventRepository.findAll().map { DataConverter.eventToDTO(it) }
    }

    override fun saveEvents(eventDTO: EventDTO): EventDTO? {
        val eventItem = DataConverter.eventFromDTO(eventDTO)
        eventItem.idEvent = UUID.randomUUID()
        val eventToSave = eventRepository.save(eventItem)
        return DataConverter.eventToDTO(eventToSave)
    }

    override fun deleteEventByTitle(title: String): EventDTO? {
        val itemToDelete = eventRepository.findEventByTitle(title)
        eventRepository.delete(itemToDelete)
        return DataConverter.eventToDTO(itemToDelete)
    }

    override fun addImageToEvent(title: String, imageName: String): EventDTO? {
        val eventItem = eventRepository.findEventByTitle(title)
        val imageItem = imageRepository.findImageByName(imageName)

        eventItem.images?.add(imageItem)
        eventRepository.save(eventItem)

        return DataConverter.eventToDTO(eventItem)
    }

    override fun deleteImageToEvent(title: String, imageName: String): EventDTO? {
        val eventItem = eventRepository.findEventByTitle(title)
        val imageItem = imageRepository.findImageByName(imageName)

        eventItem.images?.remove(imageItem)
        eventRepository.save(eventItem)

        return DataConverter.eventToDTO(eventItem)
    }
}