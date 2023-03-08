package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.PlaceControllerInterface
import com.etno.microservice.model.dto.PlaceDTO
import com.etno.microservice.service.implementation.PlaceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaceController(
    private val placeService: PlaceService
): PlaceControllerInterface {
    override fun getPlaces(): ResponseEntity<List<PlaceDTO>> {
        return ResponseEntity.ok().body(placeService.getPlaces())
    }

    override fun getPlacesByUsername(username: String): ResponseEntity<List<PlaceDTO>> {
        return ResponseEntity.ok().body(placeService.getPlacesByUsername(username))
    }

    override fun savePlace(placeDTO: PlaceDTO): ResponseEntity<PlaceDTO> {
        return ResponseEntity.ok().body(placeService.savePlace(placeDTO))
    }
}