package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.SubscriptionDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
interface SubscriptionControllerInterface {
    @ApiOperation(
        value = "Get all subscriptions",
        nickname = "getSubscriptions",
        notes = "Will prove all section with their subscription",
        tags = ["Subscription"],
        response = SubscriptionDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Subscription", response = SubscriptionDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/subscriptions"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getSubscriptions(): ResponseEntity<List<SubscriptionDTO>>

    @ApiOperation(
        value = "Save a subscription",
        nickname = "saveSubscription",
        notes = "Will save a subscription",
        tags = ["Subscription"],
        response = SubscriptionDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Subscription", response = SubscriptionDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/subscriptions"],
        consumes = ["application/json"],
        produces = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun saveSubscription(@RequestBody subscriptionDTO: SubscriptionDTO): ResponseEntity<SubscriptionDTO>

    @ApiOperation(
        value = "get a subscription by token, category and title",
        nickname = "findSubscriptionByTokenAndCategoryAndTitle",
        notes = "Will get a subscription by token, category and title",
        tags = ["Subscription"],
        response = SubscriptionDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Subscription", response = SubscriptionDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/subscriptions"],
        produces = ["application/json"],
        method = [RequestMethod.GET],
        params = ["token", "category", "title"]
    )
    fun findSubscriptionByTokenAndCategoryAndTitle(@RequestParam(name = "token", required = true) token: String, @RequestParam(name = "category", required = true) category: String, @RequestParam(name = "title", required = true) title: String): ResponseEntity<SubscriptionDTO>
}