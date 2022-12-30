package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.NewControllerInterface
import com.etno.microservice.model.dto.NewDTO
import com.etno.microservice.service.implementation.NewService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class NewController(
    private val newService: NewService
): NewControllerInterface{
    override fun getNews(): ResponseEntity<List<NewDTO>> {
        return ResponseEntity.ok().body(newService.getNews())
    }
}