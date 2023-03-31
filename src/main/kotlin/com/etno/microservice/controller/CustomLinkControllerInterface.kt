package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.CustomLinkDTO
import com.etno.microservice.model.dto.pagination.CustomLinkPageDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
interface CustomLinkControllerInterface {
    @ApiOperation(
        value = "get custom links",
        nickname = "getCustomLinks",
        notes = "Gonna see custom links",
        tags = ["Custom Link"],
        response = CustomLinkDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Custom Link", response = CustomLinkDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/custom_links"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getCustomLinks(): ResponseEntity<List<CustomLinkDTO>>

    @ApiOperation(
        value = "get custom links by username",
        nickname = "getCustomLinksByUsername",
        notes = "you can get custom links by username",
        tags = ["Custom Link"],
        response = CustomLinkDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Custom Link", response = CustomLinkDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/custom_links"],
        produces = ["application/json"],
        params = ["username"],
        method = [RequestMethod.GET]
    )
    fun getCustomLinkByLocality(
        @RequestParam(name = "username", required = true) username: String
    ): ResponseEntity<List<CustomLinkDTO>>

    @ApiOperation(
        value = "Get Custom Link by paginated",
        nickname = "getCustomLinkPaginated",
        notes = "Will show link paginated",
        tags = ["Custom Link"],
        response = CustomLinkDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Custom Link", response = CustomLinkDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/custom_links/paginated"],
        produces = ["application/json"],
        params = ["username", "pageNum", "elementSize"],
        method = [RequestMethod.GET]
    )
    fun findCustomLinkPaginated(
        @RequestParam(name = "username") username: String,
        @RequestParam(defaultValue = "0", name = "pageNum") pageNum: Int,
        @RequestParam(defaultValue = "0", name = "elementSize") elementSize: Int
    ): ResponseEntity<CustomLinkPageDTO>
}