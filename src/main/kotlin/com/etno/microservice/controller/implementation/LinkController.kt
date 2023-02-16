package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.LinkControllerInterface
import com.etno.microservice.model.dto.LinkDTO
import com.etno.microservice.model.dto.pagination.LinkPageDTO
import com.etno.microservice.service.implementation.LinkService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class LinkController(
    private val linkService: LinkService
): LinkControllerInterface {
    override fun findLinkPaginated(username: String, pageNum: Int, elementSize: Int): ResponseEntity<LinkPageDTO> {
        return ResponseEntity.ok().body(linkService.getLinkPaginated(username, pageNum, elementSize))
    }

    override fun getLinks(): ResponseEntity<List<LinkDTO>> {
        return ResponseEntity.ok().body(linkService.getLinks())
    }

    override fun findLinksByUsername(username: String): ResponseEntity<List<LinkDTO>> {
        return ResponseEntity.ok().body(linkService.findLinksByUsername(username))
    }

    override fun saveLink(linkDTO: LinkDTO): ResponseEntity<LinkDTO> {
        return ResponseEntity.ok().body(linkService.saveLink(linkDTO))
    }
}