package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.BandoDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
interface BandoControllerInterface {
    @ApiOperation(
        value = "Get all bandos",
        nickname = "getBandos",
        notes = "Will show all bandos",
        tags = ["Bando"],
        response = BandoDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Bando", response = BandoDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/bandos"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getBandos(): ResponseEntity<List<BandoDTO>>

    @ApiOperation(
        value = "Save a Bando",
        nickname = "saveBando",
        notes = "Gonna save a bando",
        tags = ["Bando"],
        response = BandoDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Bando", response = BandoDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/bandos"],
        consumes = ["application/json"],
        produces = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun saveBando(
        @RequestBody bandoDTO: BandoDTO
    ): ResponseEntity<BandoDTO>
}