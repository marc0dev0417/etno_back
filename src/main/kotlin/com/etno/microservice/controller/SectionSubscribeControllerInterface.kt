package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.SectionSubscribeDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

interface SectionSubscribeControllerInterface {
    @ApiOperation(
        value = "Get all sections subscribe",
        nickname = "getSectionsSubscribe",
        notes = "Will prove all section with their subscription",
        tags = ["Section Subscribe"],
        response = SectionSubscribeDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Section Subscribe", response = SectionSubscribeDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/section_subscribe"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getSectionsSubscribe(): ResponseEntity<List<SectionSubscribeDTO>>
}