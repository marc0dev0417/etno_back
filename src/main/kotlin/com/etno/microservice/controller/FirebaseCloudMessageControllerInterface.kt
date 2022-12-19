package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.firebase.NoteDTO
import com.etno.microservice.model.dto.firebase.NotificationDTO
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
interface FirebaseCloudMessageControllerInterface {
    @ApiOperation(
        value = "send a firebase cloud message",
        nickname = "sendNotification",
        notes = "will send notification all devices",
        tags = ["Notification"],
        response = NotificationDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Notification", response = NotificationDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server Error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/send-notification"],
        produces = ["application/json"],
        consumes = ["application/json"],
        method = [RequestMethod.POST],
    )
    fun sendNotification(@RequestBody note: NoteDTO): ResponseEntity<NotificationDTO>?
}