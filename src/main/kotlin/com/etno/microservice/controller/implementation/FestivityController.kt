package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.FestivityControllerInterface
import com.etno.microservice.model.dto.FestivityDTO
import com.etno.microservice.service.implementation.FestivityService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class FestivityController(
    private val festivityService: FestivityService
): FestivityControllerInterface {
    override fun getFestivities(): ResponseEntity<List<FestivityDTO>>? {
        return ResponseEntity.ok().body(festivityService.getFestivities())
    }
}