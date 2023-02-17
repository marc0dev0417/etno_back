package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.TourismDTO
import com.etno.microservice.model.dto.pagination.TourismPageDTO
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
interface TourismControllerInterface {
    @ApiOperation(
       value = "get paginated tourism",
       nickname = "getTourismByPaginated",
       notes = "You gonna see paginated tourism",
       tags = ["Tourism"],
       response = TourismDTO::class
    )
    @ApiResponses(
            value = [
                ApiResponse(code = 201, message = "Tourism", response = TourismDTO::class),
                ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
                ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
                ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
                ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
            ]
    )
    @RequestMapping(
            value = ["/tourism"],
            produces = ["application/json"],
            params = ["username", "pageNum", "elementSize"],
            method = [RequestMethod.GET]
    )
    fun getPaginatedTourism(
            @RequestParam(name = "username", required = true) username: String,
            @RequestParam(name = "pageNum", required = true) pageNum: Int,
            @RequestParam(name = "elementSize") elementSize: Int
    ): ResponseEntity<TourismPageDTO>


    @ApiOperation(
        value = "get a tourism list",
        nickname = "getTourism",
        notes = "Endpoint to get tourism list",
        tags = ["Tourism"],
        response = TourismDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Tourism", response = TourismDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/tourism"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getTourism(): ResponseEntity<List<TourismDTO>>?

    @ApiOperation(
        value = "get a tourism list by username",
        nickname = "getTourismByUsername",
        notes = "Endpoint to get tourism list",
        tags = ["Tourism"],
        response = TourismDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Tourism", response = TourismDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/tourism"],
        produces = ["application/json"],
        params = ["username"],
        method = [RequestMethod.GET]
    )
    fun getTourismByUsername(
        @RequestParam(name = "username", required = true) username: String
    ): ResponseEntity<List<TourismDTO>>

    @ApiOperation(
        value = "save a tourism",
        nickname = "saveTourism",
        notes = "Endpoint where you will save a tourism",
        tags = ["Tourism"],
        response = TourismDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Tourism", response = TourismDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/tourism"],
        consumes = ["application/json"],
        produces = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun saveTourism(@RequestBody tourismDTO: TourismDTO): ResponseEntity<TourismDTO>?

    /*
    @ApiOperation(
        value = "Will add an image in tourism",
        nickname = "addImageToTourism",
        notes = "Endpoint to save image to Tourism",
        tags = ["Tourism"],
        response = TourismDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Tourism", response = TourismDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/tourism/image"],
        produces = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["title", "image"]
    )
    fun addImageToTourism(@RequestParam(name = "title", required = true) title: String, @RequestParam(name = "image", required = true) image: String): ResponseEntity<TourismDTO>
     */
}