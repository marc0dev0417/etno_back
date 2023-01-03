package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.Image
import com.etno.microservice.model.dto.ImageDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

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
        consumes = ["multipart/form-data"],
        produces = ["application/json"],
        method = [RequestMethod.POST],
        params = ["section", "category", "username"]
    )
    fun saveImage(
        @RequestParam(name = "image") multipartFile: MultipartFile,
        @RequestParam(name = "section", required = true) section: String,
        @RequestParam(name = "category") category: String,
        @RequestParam(name = "username") username: String): ResponseEntity<ImageDTO>?

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

    @ApiOperation(
        value = "Delete a image by name",
        nickname = "deleteImage",
        notes = "Endpoint to delete a image",
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
        method = [RequestMethod.DELETE],
        params = ["name", "section", "locality"]
    )
    fun deleteImage(@RequestParam(name = "name", required = true) name: String, @RequestParam(name = "section", required = true) section: String, @RequestParam(name = "locality", required = true) locality: String): ResponseEntity<ImageDTO>?


    @ApiOperation(
        value = "Find images by locality",
        nickname = "findImagesByLocality",
        notes = "Endpoint to find all image list by locality",
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
        method = [RequestMethod.GET],
        params = ["locality"]
    )
    fun findImagesByLocality(@RequestParam(name = "locality") locality: String):ResponseEntity<List<ImageDTO>>
}