package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.LinkDTO
import com.etno.microservice.model.dto.pagination.LinkPageDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
interface LinkControllerInterface {
    @ApiOperation(
        value = "Get link by paginated",
        nickname = "getLinkPaginated",
        notes = "Will show link paginated",
        tags = ["Link"],
        response = LinkDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Link", response = LinkPageDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/links/paginated"],
        produces = ["application/json"],
        params = ["username", "pageNum", "elementSize"],
        method = [RequestMethod.GET]
    )
    fun findLinkPaginated(
        @RequestParam(name = "username") username: String,
        @RequestParam(defaultValue = "0", name = "pageNum") pageNum: Int,
        @RequestParam(defaultValue = "0", name = "elementSize") elementSize: Int
    ): ResponseEntity<LinkPageDTO>

    @ApiOperation(
        value = "Get all links",
        nickname = "getLinks",
        notes = "Gonna see all links",
        tags = ["Link"]
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Link", response = LinkDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/links"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getLinks(): ResponseEntity<List<LinkDTO>>

    @ApiOperation(
        value = "Get all links by username",
        nickname = "getLinksByUsername",
        notes = "Gonna see all links by username",
        tags = ["Link"]
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Link", response = LinkDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/links"],
        produces = ["application/json"],
        params = ["username"],
        method = [RequestMethod.GET]
    )
    fun findLinksByUsername(
        @RequestParam(name = "username") username: String
    ): ResponseEntity<List<LinkDTO>>

    @ApiOperation(
        value = "Save a link",
        nickname = "saveLink",
        notes = "Gonna save a link",
        tags = ["Link"]
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Link", response = LinkDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/links"],
        consumes = ["application/json"],
        produces = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun saveLink(@RequestBody linkDTO: LinkDTO): ResponseEntity<LinkDTO>
}