package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.PharmacyDTO
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
interface PharmacyControllerInterface {
    @ApiOperation(
        value = "Get all pharmacies",
        nickname = "getPharmacies",
        notes = "Will prove all pharmacies",
        tags = ["Pharmacy"],
        response = PharmacyDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Pharmacy", response = PharmacyDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/pharmacies"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getPharmacies(): ResponseEntity<List<PharmacyDTO>>

    @ApiOperation(
        value = "Get all pharmacies by username",
        nickname = "getPharmaciesByUsername",
        notes = "Will prove all pharmacies by username",
        tags = ["Pharmacy"],
        response = PharmacyDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Pharmacy", response = PharmacyDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/pharmacies"],
        produces = ["application/json"],
        params = ["username"],
        method = [RequestMethod.GET]
    )
    fun getPharmaciesByUsername(
        @RequestParam(name = "username", required = true) username: String
    ): ResponseEntity<List<PharmacyDTO>>

    @ApiOperation(
        value = "Save a pharmacy",
        nickname = "savePharmacy",
        notes = "Will save a pharmacy",
        tags = ["Pharmacy"],
        response = PharmacyDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Pharmacy", response = PharmacyDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/pharmacies"],
        produces = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun savePharmacy(@RequestBody pharmacyDTO: PharmacyDTO): ResponseEntity<PharmacyDTO>

}