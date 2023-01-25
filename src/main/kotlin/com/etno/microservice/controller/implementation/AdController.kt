package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.AdControllerInterface
import com.etno.microservice.model.dto.AdDTO
import com.etno.microservice.service.implementation.AdService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AdController(
    private val adService: AdService
): AdControllerInterface {
    override fun getAds(): ResponseEntity<List<AdDTO>> {
        return ResponseEntity.ok().body(adService.getAds())
    }

    override fun getAdsByUsername(username: String): ResponseEntity<List<AdDTO>> {
        return ResponseEntity.ok().body(adService.getAdsByUsername(username))
    }
}