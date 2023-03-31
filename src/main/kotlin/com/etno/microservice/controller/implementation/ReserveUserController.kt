package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.ReserveUserControllerInterface
import com.etno.microservice.model.dto.ReserveUserDTO
import com.etno.microservice.service.implementation.ReserveUserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ReserveUserController(
    private val reserveUserService: ReserveUserService
): ReserveUserControllerInterface {
    override fun getReserveUsersByFcmToken(fcmToken: String): ResponseEntity<List<ReserveUserDTO>> {
        return ResponseEntity.ok().body(reserveUserService.getReserveUsersByFcmToken(fcmToken))
    }
}