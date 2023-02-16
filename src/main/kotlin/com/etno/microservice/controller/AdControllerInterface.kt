package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.AdDTO
import com.etno.microservice.model.dto.EventDTO
import com.etno.microservice.model.dto.pagination.AdPageDTO
import com.etno.microservice.model.dto.pagination.EventPageDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
interface AdControllerInterface {

    @ApiOperation(
        value = "Get all ads",
        nickname = "getAds",
        notes = "Will show all ads",
        tags = ["Ad"],
        response = AdDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Ad", response = AdDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/ads"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getAds(): ResponseEntity<List<AdDTO>>?

    @ApiOperation(
        value = "Get ads by paginated",
        nickname = "getAdsPaginated",
        notes = "Will show ads paginated",
        tags = ["Ad"],
        response = AdDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Ad", response = AdPageDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/ads"],
        produces = ["application/json"],
        params = ["username", "pageNum", "elementSize"],
        method = [RequestMethod.GET]
    )
    fun findAdsPaginated(
        @RequestParam(name = "username") username: String,
        @RequestParam(defaultValue = "0", name = "pageNum") pageNum: Int,
        @RequestParam(defaultValue = "0", name = "elementSize") elementSize: Int
    ): ResponseEntity<AdPageDTO>

    @ApiOperation(
        value = "Get all ads by username",
        nickname = "getAds",
        notes = "Will show all ads",
        tags = ["Ad"],
        response = AdDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Ad", response = AdDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/ads"],
        produces = ["application/json"],
        params = ["username"],
        method = [RequestMethod.GET]
    )

    fun getAdsByUsername(
        @RequestParam(name = "username", required = true) username: String
    ): ResponseEntity<List<AdDTO>>
}