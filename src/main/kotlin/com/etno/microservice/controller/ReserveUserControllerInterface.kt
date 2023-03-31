package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.ReserveUser
import com.etno.microservice.model.dto.ReserveUserDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
interface ReserveUserControllerInterface {
    @ApiOperation(
        value = "get reserve users by fcmToken",
        nickname = "getReserveUsersByFcmToken",
        notes = "You could get reserve users by fcmToken",
        tags = ["Reserve User"],
        response = ReserveUser::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Reserve User", response = ReserveUserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/reserveUsers"],
        produces = ["application/json"],
        params = ["fcmToken"],
        method = [RequestMethod.GET]
    )
    fun getReserveUsersByFcmToken(
        @RequestParam(name = "fcmToken", required = true) fcmToken: String
    ): ResponseEntity<List<ReserveUserDTO>>

}