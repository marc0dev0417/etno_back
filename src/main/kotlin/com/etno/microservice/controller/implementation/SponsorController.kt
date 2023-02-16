package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.SponsorControllerInterface
import com.etno.microservice.model.dto.SponsorDTO
import com.etno.microservice.model.dto.pagination.SponsorPageDTO
import com.etno.microservice.service.implementation.SponsorService
import io.swagger.models.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class SponsorController(
    private val sponsorService: SponsorService
): SponsorControllerInterface {
    override fun findSponsorPaginated(username: String, pageNum: Int, elementSize: Int): ResponseEntity<SponsorPageDTO> {
        return ResponseEntity.ok().body(sponsorService.getSponsorPaginated(username, pageNum, elementSize))
    }

    override fun getSponsors(): ResponseEntity<List<SponsorDTO>> {
        return ResponseEntity.ok().body(sponsorService.getSponsors())
    }

    override fun findSponsorsByUsername(username: String): ResponseEntity<List<SponsorDTO>> {
        return ResponseEntity.ok().body(sponsorService.findSponsorsByUsername(username))
    }
}