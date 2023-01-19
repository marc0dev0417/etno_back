package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.LinkDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
interface LinkControllerInterface {
    @ApiOperation(
        value = "Get all links",
        nickname = "getLinks",
        notes = "Gonna see all links",
        tags = ["Link"]
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Link", response = LinkDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/links"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getLinks(): ResponseEntity<List<LinkDTO>>

    @ApiOperation(
        value = "Save a link",
        nickname = "saveLink",
        notes = "Gonna save a link",
        tags = ["Link"]
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Link", response = LinkDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/links"],
        consumes = ["application/json"],
        produces = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun saveLink(@RequestBody linkDTO: LinkDTO): ResponseEntity<LinkDTO>
}