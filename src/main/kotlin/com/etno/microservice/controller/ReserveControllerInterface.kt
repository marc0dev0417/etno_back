package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.ReserveDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
interface ReserveControllerInterface {
    @ApiOperation(
        value = "Get reserves",
        nickname = "getReserves",
        notes = "You can get reserves",
        tags = ["Reserve"],
        response = ReserveDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "reserve", response = ReserveDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message  ="Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/reserves"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getReserves(): ResponseEntity<List<ReserveDTO>>
}