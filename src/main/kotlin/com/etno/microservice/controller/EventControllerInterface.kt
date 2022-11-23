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
        method = [RequestMethod.POST]
    )
    fun saveEvent(@RequestBody eventDTO: EventDTO): ResponseEntity<EventDTO>?
}