package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.LinkControllerInterface
import com.etno.microservice.model.dto.LinkDTO
import com.etno.microservice.service.implementation.LinkService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class LinkController(
    private val linkService: LinkService
): LinkControllerInterface {
    override fun getLinks(): ResponseEntity<List<LinkDTO>> {
        return ResponseEntity.ok().body(linkService.getLinks())
    }

    override fun saveLink(linkDTO: LinkDTO): ResponseEntity<LinkDTO> {
        return ResponseEntity.ok().body(linkService.saveLink(linkDTO))
    }
}