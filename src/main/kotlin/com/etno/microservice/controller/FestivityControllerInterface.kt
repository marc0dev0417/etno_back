package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.FestivityDTO
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
interface FestivityControllerInterface {
    @ApiOperation(
        value = "Get all festivities",
        nickname = "getFestivities",
        notes = "Show all festivities",
        tags = ["Festivity"],
        response = FestivityDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Festivity", response = FestivityDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server Error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/festivities"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getFestivities(): ResponseEntity<List<FestivityDTO>>?

    @ApiOperation(
        value = "save festivity",
        nickname = "saveFestivity",
        notes = "Save a festivity",
        tags = ["Festivity"],
        response = FestivityDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Festivity", response = FestivityDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server Error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/festivities"],
        produces = ["application/json"],
        consumes = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun saveFestivity(@RequestBody festivityDTO: FestivityDTO): ResponseEntity<FestivityDTO>?

    @ApiOperation(
        value = "delete festivity",
        nickname = "deleteFestivity",
        notes = "delete a festivity",
        tags = ["Festivity"],
        response = FestivityDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Festivity", response = FestivityDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server Error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/festivities"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["title"]
    )
    fun deleteFestivity(@RequestParam("title", required = true) title: String): ResponseEntity<FestivityDTO>?

    @ApiOperation(
        value = "add image to festivity",
        nickname = "addImageToFestivity",
        notes = "add image for festivity",
        tags = ["Festivity"],
        response = FestivityDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Festivity", response = FestivityDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server Error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/festivities/image"],
        produces = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["title", "image"]
    )
    fun addImageToFestivity(@RequestParam("title", required = true) title: String, @RequestParam("image") name: String): ResponseEntity<FestivityDTO>?

    @ApiOperation(
        value = "delete image from Festivity",
        nickname = "deleteImageFromFestivity",
        notes = "delete image from festivity",
        tags = ["Festivity"],
        response = FestivityDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Festivity", response = FestivityDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server Error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/festivities/image/remove"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["title", "image"]
    )
    fun deleteImageFromFestivity(@RequestParam("title", required = true) title: String, @RequestParam("image", required = true) imageName: String): ResponseEntity<FestivityDTO>?
}