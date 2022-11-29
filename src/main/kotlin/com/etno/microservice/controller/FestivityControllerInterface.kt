package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.Festivity
import com.etno.microservice.model.dto.FestivityDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
interface FestivityControllerInterface {
    @ApiOperation(
        value = "Get all festivities",
        nickname = "getFestivities",
        notes = "Show all festivities",
        tags = ["Festivity"],
        response = Festivity::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Festivity", response = Festivity::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server Error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/festivities"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getFestivities(): ResponseEntity<List<FestivityDTO>>?
}