package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.ReserveControllerInterface
import com.etno.microservice.model.dto.ReserveDTO
import com.etno.microservice.service.implementation.ReserveService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ReserveController(
    private val reserveService: ReserveService
): ReserveControllerInterface {
    override fun getReserves(): ResponseEntity<List<ReserveDTO>> {
        return ResponseEntity.ok().body(reserveService.getReserves())
    }
}