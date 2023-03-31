package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.mail.MailDetailsDTO
import com.etno.microservice.model.dto.mail.MailDetailsSuccessDTO
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
interface MailControllerInterface {
    @ApiOperation(
        value = "Send a Mail",
        nickname = "sendMail",
        notes = "Gonna send a simple mail",
        tags = ["Mail"],
        response = MailDetailsDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Mail", response = MailDetailsDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/sendMail"],
        consumes = ["application/json"],
        produces = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun senMail(@RequestBody mailDetailsDTO: MailDetailsDTO): ResponseEntity<MailDetailsSuccessDTO>

    @ApiOperation(
        value = "Send a Mail with attachment",
        nickname = "sendMailWithAttachment",
        notes = "Gonna send a mail with attachment",
        tags = ["Mail"],
        response = MailDetailsDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Mail", response = MailDetailsDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/sendMail/attachment"],
        produces = ["application/json"],
        params = ["address", "message", "subject", "attachment"],
        method = [RequestMethod.POST]
    )
    fun sendMailWithAttachment(
        @RequestParam(name = "address", required = true) address: String,
        @RequestParam(name = "message", required = true) message: String,
        @RequestParam(name = "subject", required = true) subject: String,
        @RequestParam(name = "attachment", required = true) attachment: String,
    ): ResponseEntity<MailDetailsSuccessDTO>
}