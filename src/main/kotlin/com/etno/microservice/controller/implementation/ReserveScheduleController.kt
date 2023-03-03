package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.ReserveScheduleControllerInterface
import com.etno.microservice.model.dto.ReserveScheduleDTO
import com.etno.microservice.service.implementation.ReserveScheduleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ReserveScheduleController(
    private val reserveScheduleService: ReserveScheduleService
): ReserveScheduleControllerInterface {
    override fun getReserveSchedules(): ResponseEntity<List<ReserveScheduleDTO>> {
        return ResponseEntity.ok().body(reserveScheduleService.getReserveSchedules())
    }
}