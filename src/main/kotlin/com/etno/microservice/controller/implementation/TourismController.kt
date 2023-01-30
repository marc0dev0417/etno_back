package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.TourismControllerInterface
import com.etno.microservice.model.dto.TourismDTO
import com.etno.microservice.service.implementation.TourismService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class TourismController(
    private val tourismService: TourismService
): TourismControllerInterface {
    override fun getTourism(): ResponseEntity<List<TourismDTO>>? {
        return ResponseEntity.ok().body(tourismService.getTourism())
    }

    override fun getTourismByUsername(username: String): ResponseEntity<List<TourismDTO>> {
        return ResponseEntity.ok().body(tourismService.getTourismByUsername(username))
    }

    override fun saveTourism(tourismDTO: TourismDTO): ResponseEntity<TourismDTO>? {
        return ResponseEntity.ok().body(tourismService.saveTourism(tourismDTO))
    }
    /*
    override fun addImageToTourism(title: String, image: String): ResponseEntity<TourismDTO> {
        return ResponseEntity.ok().body(tourismService.addImageToTourism(title = title, imageName = image))
    }
     */
}