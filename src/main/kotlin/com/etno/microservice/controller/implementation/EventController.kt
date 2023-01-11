package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.EventControllerInterface
import com.etno.microservice.model.dto.EventDTO
import com.etno.microservice.service.implementation.EventService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class EventController(
    private val eventService: EventService
): EventControllerInterface {
    override fun getEvents(): ResponseEntity<List<EventDTO>>? {
        return ResponseEntity.ok().body(eventService.getEvents())
    }

    override fun getEventByUsernameAndTitle(username: String, title: String): ResponseEntity<EventDTO> {
        return ResponseEntity.ok().body(eventService.findEventByTitleAndUsername(title, username))
    }

    override fun saveEvent(
        eventDTO: EventDTO
    ): ResponseEntity<EventDTO>? {
        return ResponseEntity.ok().body(eventService.saveEvents(eventDTO))
    }

    override fun addImageToEvent(username: String, title: String, imageName: String): ResponseEntity<EventDTO>? {
        return ResponseEntity.ok().body(eventService.addImageToEvent(username, title, imageName))
    }

    override fun deleteEvent(username: String, title: String): ResponseEntity<EventDTO>? {
        return ResponseEntity.ok().body(eventService.deleteEventByTitleAndUsername(username, title))
    }

    override fun removeImageToEvent(username: String, title: String, imageName: String): ResponseEntity<EventDTO>? {
        return ResponseEntity.ok().body(eventService.deleteImageToEvent(username,title, imageName))
    }
}