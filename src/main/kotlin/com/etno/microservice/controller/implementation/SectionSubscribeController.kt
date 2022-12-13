package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.SectionSubscribeControllerInterface
import com.etno.microservice.model.dto.SectionSubscribeDTO
import com.etno.microservice.service.implementation.SectionSubscribeService
import org.springframework.http.ResponseEntity

class SectionSubscribeController(
    private val sectionSubscribeService: SectionSubscribeService
): SectionSubscribeControllerInterface {
    override fun getSectionsSubscribe(): ResponseEntity<List<SectionSubscribeDTO>> {
        return ResponseEntity.ok().body(sectionSubscribeService.getSectionSubscribe())
    }
}