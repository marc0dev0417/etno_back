package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.EventDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
interface EventControllerInterface {
    @ApiOperation(
        value = "Get all events",
        nickname = "getEvents",
        notes = "Will show all events",
        tags = ["Event"],
        response = EventDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Event", response = EventDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/events"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getEvents(): ResponseEntity<List<EventDTO>>?

    @ApiOperation(
        value = "Get all events by username",
        nickname = "getEventsByUsername",
        notes = "Will show events by username",
        tags = ["Event"],
        response = EventDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Event", response = EventDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/events"],
        produces = ["application/json"],
        params = ["username"],
        method = [RequestMethod.GET]
    )
    fun getEventsFindUsername(
        @RequestParam(name = "username", required = true) username: String
    ): ResponseEntity<List<EventDTO>>

    @ApiOperation(
        value = "Get a Event by username and title",
        nickname = "getEvent",
        notes = "You gonna find a event by username and title",
        tags = ["Event"],
        response = EventDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Event", response = EventDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/events"],
        produces = ["application/json"],
        method = [RequestMethod.GET],
        params = ["username", "title"]
    )
    fun getEventByUsernameAndTitle(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "title", required = true) title: String
    ): ResponseEntity<EventDTO>

    @ApiOperation(
        value = "Save a event",
        nickname = "saveEvent",
        notes = "this endpoint is to save a event",
        tags = ["Event"],
        response = EventDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Event", response = EventDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/events"],
        consumes = ["application/json"],
        produces = ["application/json"],
        method = [RequestMethod.POST],
    )
    fun saveEvent(@RequestBody eventDTO: EventDTO): ResponseEntity<EventDTO>?

    @ApiOperation(
        value = "add event image",
        nickname = "addEvent",
        notes = "Endpoint to add image from event",
        tags = ["Event"],
        response = EventDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Event", response = EventDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/events/image"],
        produces = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["username", "title", "image"]
    )
    fun addImageToEvent(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "title", required = true) title: String, @RequestParam(name = "image", required = true) imageName: String): ResponseEntity<EventDTO>?

    @ApiOperation(
        value = "delete a event",
        nickname = "deleteEvent",
        notes = "Endpoint to delete an event",
        tags = ["Event"],
        response = EventDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Event", response = EventDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["events"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "title"]
    )
    fun deleteEvent(@RequestParam("username", required = true) username: String, @RequestParam("title", required = true) title: String): ResponseEntity<EventDTO>?

    @ApiOperation(
        value = "delete event image",
        nickname = "deleteEvent",
        notes = "Endpoint to delete image from event",
        tags = ["Event"],
        response = EventDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Event", response = EventDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/events/image/remove"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "title", "image"]
    )
    fun removeImageToEvent(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "title", required = true) title: String, @RequestParam(name = "image", required = true) imageName: String): ResponseEntity<EventDTO>?
}