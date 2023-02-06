package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.SponsorDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
interface SponsorControllerInterface {
    @ApiOperation(
        value = "Get all sponsors",
        nickname = "getSponsors",
        notes = "Gonna be watching all sponsors",
        tags = ["Sponsor"],
        response = SponsorDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Sponsor", response = SponsorDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/sponsors"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getSponsors(): ResponseEntity<List<SponsorDTO>>

    @ApiOperation(
        value = "Find sponsors by username",
        nickname = "findSponsorsByUsername",
        notes = "Gonna find sponsors by username",
        tags = ["Sponsor"],
        response = SponsorDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Sponsor", response = SponsorDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/sponsors"],
        produces = ["application/json"],
        params = ["username"],
        method = [RequestMethod.GET]
    )
    fun findSponsorsByUsername(
        @RequestParam(name = "username", required = true) username: String
    ): ResponseEntity<List<SponsorDTO>>
}