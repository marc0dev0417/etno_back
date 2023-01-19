package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.BandoControllerInterface
import com.etno.microservice.model.dto.BandoDTO
import com.etno.microservice.service.implementation.BandoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class BandoController(
    private val bandoService: BandoService
): BandoControllerInterface {
    override fun getBandos(): ResponseEntity<List<BandoDTO>> {
        return ResponseEntity.ok().body(bandoService.getBando())
    }

    override fun saveBando(bandoDTO: BandoDTO): ResponseEntity<BandoDTO> {
        return ResponseEntity.ok().body(bandoService.saveBando(bandoDTO))
    }

    override fun getBandosByUsername(username: String): ResponseEntity<List<BandoDTO>> {
        return ResponseEntity.ok().body(bandoService.getBandosByUsername(username))
    }

    override fun getBandoByUsername(username: String, title: String): ResponseEntity<BandoDTO> {
        return ResponseEntity.ok().body(bandoService.getBandoByUsernameAndTitle(username, title))
    }
}