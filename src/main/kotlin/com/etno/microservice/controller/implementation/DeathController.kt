package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.DeathControllerInterface
import com.etno.microservice.model.dto.DeathDTO
import com.etno.microservice.model.dto.pagination.DeathPageDTO
import com.etno.microservice.service.implementation.DeathService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class DeathController(
    private val deathService: DeathService
): DeathControllerInterface {
    override fun findDeathPaginated(username: String, pageNum: Int, elementSize: Int): ResponseEntity<DeathPageDTO> {
        return ResponseEntity.ok().body(deathService.getDeathPaginated(username,pageNum,elementSize))
    }

    override fun findDeaths(): ResponseEntity<List<DeathDTO>> {
       return ResponseEntity.ok().body(deathService.getDeaths())
    }

    override fun findDeathsByUsername(username: String): ResponseEntity<List<DeathDTO>> {
        return ResponseEntity.ok().body(deathService.getDeathsByUsername(username))
    }
}