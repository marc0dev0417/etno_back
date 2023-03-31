package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.IncidentDTO
import com.etno.microservice.model.dto.pagination.IncidentPageDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
interface IncidenceControllerInterface {
    @ApiOperation(
        value = "Get all incidences",
        nickname = "getIncidents",
        notes = "Gonna see all incidents",
        tags = ["Incident"]
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Incidence", response = IncidentDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/incidents"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getIncidences(): ResponseEntity<List<IncidentDTO>>

    @ApiOperation(
        value = "Get incidents by username, fcmToken and title",
        nickname = "getIncidentsByUsernameAndFcmTokenAndTitle",
        notes = "Gonna see all incidents by username, fcmToken and title",
        tags = ["Incident"]
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Incidence", response = IncidentDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/incidents/villager"],
        produces = ["application/json"],
        params = ["username", "fcmToken"],
        method = [RequestMethod.GET]
    )
    fun getIncidentsByUsernameAndFcmToken(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "fcmToken", required = true) fcmToken: String
    ): ResponseEntity<List<IncidentDTO>>

    @ApiOperation(
        value = "Get incidents by paginated",
        nickname = "getIncidentsPaginated",
        notes = "Will show incidents paginated",
        tags = ["Incident"],
        response = IncidentDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Incidence", response = IncidentPageDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/incidents/paginated"],
        produces = ["application/json"],
        params = ["username", "pageNum", "elementSize"],
        method = [RequestMethod.GET]
    )
    fun findIncidentsPaginated(
        @RequestParam(name = "username") username: String,
        @RequestParam(defaultValue = "0", name = "pageNum") pageNum: Int,
        @RequestParam(defaultValue = "0", name = "elementSize") elementSize: Int
    ): ResponseEntity<IncidentPageDTO>
}