package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.HallDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
interface HallControllerInterface {

    @ApiOperation(
        value = "Get halls",
        nickname = "getHalls",
        notes = "Gonna see all halls",
        tags = ["Hall"]
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Hall", response = HallDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/halls"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getHalls(): ResponseEntity<List<HallDTO>>

    @ApiOperation(
        value = "Save a hall",
        nickname = "saveHall",
        notes = "Gonna save to hall",
        tags = ["Hall"]
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Hall", response = HallDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/halls"],
        consumes = ["application/json"],
        produces = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun saveHall(@RequestBody hallDTO: HallDTO): ResponseEntity<HallDTO>
}