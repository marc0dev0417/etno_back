package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.ServiceDTO
import com.etno.microservice.model.dto.pagination.ServicePageDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
interface ServiceControllerInterface {
    @ApiOperation(
        value = "Get services by paginated",
        nickname = "getServicePaginated",
        notes = "Will show services paginated",
        tags = ["Service"],
        response = ServiceDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Service", response = ServicePageDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/services"],
        produces = ["application/json"],
        params = ["username", "pageNum", "elementSize"],
        method = [RequestMethod.GET]
    )
    fun findServicePaginated(
        @RequestParam(name = "username") username: String,
        @RequestParam(defaultValue = "0", name = "pageNum") pageNum: Int,
        @RequestParam(defaultValue = "0", name = "elementSize") elementSize: Int
    ): ResponseEntity<ServicePageDTO>

    @ApiOperation(
        value = "Get all services",
        nickname = "getServices",
        notes = "Will show all services",
        tags = ["Service"],
        response = ServiceDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Service", response = ServiceDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/services"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun findServices(): ResponseEntity<List<ServiceDTO>>

    @ApiOperation(
        value = "Get all services by username and category",
        nickname = "getServicesUsernameAndCategory",
        notes = "Will show all services by username and category",
        tags = ["Service"],
        response = ServiceDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Service", response = ServiceDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/services"],
        produces = ["application/json"],
        params = ["username", "category"],
        method = [RequestMethod.GET]
    )
    fun getServicesByUsernameAndCategory(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "category", required = true) category: String
    ): ResponseEntity<List<ServiceDTO>>
}