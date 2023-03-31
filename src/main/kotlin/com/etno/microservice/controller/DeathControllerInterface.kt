package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.DeathDTO
import com.etno.microservice.model.dto.pagination.DeathPageDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
interface DeathControllerInterface {
    @ApiOperation(
        value = "Get death by paginated",
        nickname = "getDeathPaginated",
        notes = "Will show ads paginated",
        tags = ["Death"],
        response = DeathDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Death", response = DeathPageDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/deaths/paginated"],
        produces = ["application/json"],
        params = ["username", "pageNum", "elementSize"],
        method = [RequestMethod.GET]
    )
    fun findDeathPaginated(
        @RequestParam(name = "username") username: String,
        @RequestParam(defaultValue = "0", name = "pageNum") pageNum: Int,
        @RequestParam(defaultValue = "0", name = "elementSize") elementSize: Int
    ): ResponseEntity<DeathPageDTO>
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

    @ApiOperation(
        value = "Get all deaths by username",
        nickname = "getDeathsByUsername",
        notes = "Will show all deaths by username",
        tags = ["Deaths"],
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
        params = ["username"],
        method = [RequestMethod.GET]
    )
    fun findDeathsByUsername(username: String): ResponseEntity<List<DeathDTO>>
}