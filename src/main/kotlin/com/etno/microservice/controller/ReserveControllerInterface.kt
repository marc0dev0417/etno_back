package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.ReserveDTO
import com.etno.microservice.model.dto.pagination.ReservePageDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

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


    @ApiOperation(
        value = "Get reserves by username",
        nickname = "getReservesByUsername",
        notes = "You can get reserves by username",
        tags = ["Reserve"],
        response = ReserveDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Reserve", response = ReserveDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["reserves"],
        produces = ["application/json"],
        params = ["username"],
        method = [RequestMethod.GET]
    )
    fun getReservesByUsername(@RequestParam(name = "username", required = true) username: String): ResponseEntity<List<ReserveDTO>>

    @ApiOperation(
        value = "Get reserves by paginated",
        nickname = "getReservesPaginated",
        notes = "Will show reserves paginated",
        tags = ["Reserve"],
        response = ReserveDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Reserve", response = ReservePageDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/reserves/paginated"],
        produces = ["application/json"],
        params = ["username", "pageNum", "elementSize"],
        method = [RequestMethod.GET]
    )
    fun findReservesPaginated(
        @RequestParam(name = "username") username: String,
        @RequestParam(defaultValue = "0", name = "pageNum") pageNum: Int,
        @RequestParam(defaultValue = "0", name = "elementSize") elementSize: Int
    ): ResponseEntity<ReservePageDTO>
}