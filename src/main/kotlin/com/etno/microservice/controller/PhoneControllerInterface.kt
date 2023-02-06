package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.PhoneDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import jdk.jfr.Category
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
interface PhoneControllerInterface {
    @ApiOperation(
        value = "Get all phones",
        nickname = "getPhones",
        notes = "Will show all phones",
        tags = ["Phone"],
        response = PhoneDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Phone", response = PhoneDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/phones"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun findPhones(): ResponseEntity<List<PhoneDTO>>

    @ApiOperation(
        value = "Get all phone by username and category",
        nickname = "getPhonesUsernameAndCategory",
        notes = "Will show all phones by username and category",
        tags = ["Phone"],
        response = PhoneDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Phone", response = PhoneDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/phones"],
        produces = ["application/json"],
        params = ["username", "category"],
        method = [RequestMethod.GET]
    )
    fun getPhonesByUsernameAndCategory(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "category", required = true) category: String
    ): ResponseEntity<List<PhoneDTO>>
}