package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.PlaceDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
interface PlaceControllerInterface {
    @ApiOperation(
        value = "Get places",
        nickname = "getPlaces",
        notes = "Will get places",
        tags = ["Place"],
        response = PlaceDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Place", response = PlaceDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/places"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getPlaces(): ResponseEntity<List<PlaceDTO>>
}