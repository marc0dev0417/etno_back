package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.SubscriptionUserDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
interface SubscriptionUserControllerInterface {
    @ApiOperation(
        value = "Get all subscription user",
        nickname = "getSubscriptionUsers",
        notes = "Will prove all section with their subscription",
        tags = ["Subscription User"],
        response = SubscriptionUserDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Subscription User", response = SubscriptionUserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/subscription_users"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getSubscriptionUsers(): ResponseEntity<List<SubscriptionUserDTO>>

    @ApiOperation(
        value = "Save subscription user",
        nickname = "saveSubscriptionUser",
        notes = "Will save subscription user",
        tags = ["Subscription User"],
        response = SubscriptionUserDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Subscription User", response = SubscriptionUserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/subscription_users"],
        produces = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun saveSubscriptionUser(@RequestBody subscriptionUserDTO: SubscriptionUserDTO): ResponseEntity<SubscriptionUserDTO>
}