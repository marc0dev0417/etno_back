package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.DeathDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
interface DeathControllerInterface {
    @ApiOperation(
        value = "Get all deaths",
        nickname = "getDeaths",
        notes = "Will show all deaths",
        tags = ["Death"],
        response = DeathDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Death", response = DeathDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/deaths"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun findDeaths(): ResponseEntity<List<DeathDTO>>
}