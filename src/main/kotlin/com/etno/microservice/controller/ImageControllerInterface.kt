package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.EventDTO
import com.etno.microservice.model.dto.ImageDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
interface ImageControllerInterface {
    @ApiOperation(
        value = "Save a image",
        nickname = "saveImage",
        notes = "this endpoint is to save a image",
        tags = ["Image"],
        response = ImageDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Image", response = ImageDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/images"],
        consumes = ["application/json"],
        produces = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun saveImage(@RequestBody imageDTO: ImageDTO): ResponseEntity<ImageDTO>?

    @ApiOperation(
        value = "get a list about image",
        nickname = "getImages",
        notes = "Endpoint to get a list of images",
        tags = ["Image"],
        response = ImageDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Image", response = ImageDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/images"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getImages(): ResponseEntity<List<ImageDTO>>?
}