package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.HallControllerInterface
import com.etno.microservice.model.dto.HallDTO
import com.etno.microservice.service.implementation.HallService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class HallController(
    private val hallService: HallService
): HallControllerInterface {
    override fun getHalls(): ResponseEntity<List<HallDTO>> {
        return ResponseEntity.ok().body(hallService.getHalls())
    }
    override fun saveHall(hallDTO: HallDTO): ResponseEntity<HallDTO> {
        return ResponseEntity.ok().body(hallService.saveHall(hallDTO))
    }
}