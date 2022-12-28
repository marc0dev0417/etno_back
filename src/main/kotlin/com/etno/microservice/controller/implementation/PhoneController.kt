package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.PhoneControllerInterface
import com.etno.microservice.model.dto.PhoneDTO
import com.etno.microservice.service.implementation.PhoneService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PhoneController(
    private val phoneService: PhoneService
): PhoneControllerInterface {
    override fun findPhones(): ResponseEntity<List<PhoneDTO>> {
        return ResponseEntity.ok().body(phoneService.getPhones())
    }
}