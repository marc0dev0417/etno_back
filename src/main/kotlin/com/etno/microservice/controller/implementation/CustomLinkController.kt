package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.CustomLinkControllerInterface
import com.etno.microservice.model.dto.CustomLinkDTO
import com.etno.microservice.model.dto.pagination.CustomLinkPageDTO
import com.etno.microservice.service.implementation.CustomLinkService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomLinkController(
    private val customLinkService: CustomLinkService
): CustomLinkControllerInterface{
    override fun getCustomLinks(): ResponseEntity<List<CustomLinkDTO>> {
        return ResponseEntity.ok().body(customLinkService.getCustomLinks())
    }

    override fun getCustomLinkByLocality(username: String): ResponseEntity<List<CustomLinkDTO>> {
        return ResponseEntity.ok().body(customLinkService.getCustomLinksByLocality(username))
    }

    override fun findCustomLinkPaginated(
        username: String,
        pageNum: Int,
        elementSize: Int
    ): ResponseEntity<CustomLinkPageDTO> {
        return ResponseEntity.ok().body(customLinkService.getCustomLinkPaginated(username, pageNum, elementSize))
    }
}