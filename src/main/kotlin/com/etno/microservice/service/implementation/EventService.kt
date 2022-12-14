package com.etno.microservice.service.implementation

import com.etno.microservice.exception.Constants
import com.etno.microservice.exception.handler.ListEmptyException
import com.etno.microservice.model.dto.EventDTO
import com.etno.microservice.repository.EventRepository
import com.etno.microservice.repository.FCMTokenRepository
import com.etno.microservice.repository.ImageRepository
import com.etno.microservice.service.EventServiceInterface
import com.etno.microservice.util.DataConverter
import com.etno.microservice.util.Urls
import org.apache.catalina.util.URLEncoder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.nio.charset.StandardCharsets
import java.util.*

@Service
class EventService(
    @Autowired
    private val eventRepository: EventRepository,

    @Autowired
    private val imageRepository: ImageRepository,

    @Autowired
    private val fcmTokenRepository: FCMTokenRepository

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

        if (fcmTokenRepository.findAll().isNotEmpty()){
            val restTemplate = RestTemplate()
            val map: Map<String, String> = mapOf("subject" to "Nuevo evento", "content" to "Evento ${eventToSave.title} disponible")

            val response: ResponseEntity<Void> = restTemplate.postForEntity(Urls.urlSendNotification, map, Void::class.java)
        }

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