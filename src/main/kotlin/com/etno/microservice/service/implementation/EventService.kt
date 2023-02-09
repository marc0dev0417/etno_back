package com.etno.microservice.service.implementation

import com.etno.microservice.exception.Constants
import com.etno.microservice.exception.handler.ListEmptyException
import com.etno.microservice.model.dto.EventDTO
import com.etno.microservice.model.dto.pagination.EventPageDTO
import com.etno.microservice.repository.EventRepository
import com.etno.microservice.repository.FCMTokenRepository
import com.etno.microservice.repository.ImageRepository
import com.etno.microservice.service.EventServiceInterface
import com.etno.microservice.util.DataConverter
import com.etno.microservice.util.Urls
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val imageRepository: ImageRepository,
    private val fcmTokenRepository: FCMTokenRepository
): EventServiceInterface {
    override fun getEvents(): List<EventDTO>? {
        if(eventRepository.findAll().isEmpty()){
            throw ListEmptyException(Constants.LIST_EMPTY.code, Constants.LIST_EMPTY)
        }
        return eventRepository.findAll().map { DataConverter.eventToDTO(it) }
    }

    override fun findEventByTitleAndUsername(title: String, username: String): EventDTO? {
        return DataConverter.eventToDTO(eventRepository.findEventByTitleAndUsername(title, username)!!)
    }

    override fun findEventsByUsername(username: String): List<EventDTO>? {
        return eventRepository.findEventsByUsername(username)?.map { DataConverter.eventToDTO(it) }
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

    override fun deleteEventByTitleAndUsername(title: String, username: String): EventDTO? {
        val itemToDelete = eventRepository.findEventByTitleAndUsername(title, username)
        eventRepository.delete(itemToDelete!!)
        return DataConverter.eventToDTO(itemToDelete)
    }

    override fun addImageToEvent(username: String, title: String, imageName: String): EventDTO? {
        val eventItem = eventRepository.findEventByTitleAndUsername(title, username)
        val imageItem = imageRepository.findImageByName(imageName)

        eventItem?.images?.add(imageItem!!)
        val itemToSaved = eventRepository.save(eventItem!!)

        return DataConverter.eventToDTO(itemToSaved)
    }

    override fun deleteImageToEvent(username: String, title: String, imageName: String): EventDTO? {
        val eventItem = eventRepository.findEventByTitleAndUsername(title, username)
        val imageItem = imageRepository.findImageByName(imageName)

        eventItem?.images?.remove(imageItem)
        eventRepository.save(eventItem!!)

        return DataConverter.eventToDTO(eventItem)
    }

    override fun getEventsPaginated(username: String, pageNum: Int, pageSize: Int): EventPageDTO? {
        val pageable = PageRequest.of(pageNum, pageSize)
        val pagedResult = eventRepository.findEventsPageableByUsername(username, pageable)
        return if (pagedResult!!.hasContent()){
            EventPageDTO(
                content = pagedResult.content.toList().map { DataConverter.eventToDTO(it) },
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        }else{
            EventPageDTO(
                content = emptyList(),
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        }
    }
}