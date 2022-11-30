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

    override fun saveFestivity(festivityDTO: FestivityDTO): ResponseEntity<FestivityDTO>? {
        return ResponseEntity.ok().body(festivityService.saveFestivity(festivityDTO))
    }

    override fun deleteFestivity(title: String): ResponseEntity<FestivityDTO>? {
        return ResponseEntity.ok().body(festivityService.deleteFestivityByTitle(title))
    }

    override fun addImageToFestivity(title: String, name: String): ResponseEntity<FestivityDTO>? {
        return ResponseEntity.ok().body(festivityService.addImageToFestivity(title, name))
    }

    override fun deleteImageFromFestivity(title: String, imageName: String): ResponseEntity<FestivityDTO>? {
        return ResponseEntity.ok().body(festivityService.deleteImageFromFestivity(title, imageName))
    }
}