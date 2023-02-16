package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.ServiceControllerInterface
import com.etno.microservice.model.dto.ServiceDTO
import com.etno.microservice.model.dto.pagination.ServicePageDTO
import com.etno.microservice.service.implementation.ServiceService
import io.swagger.models.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ServiceController(
    private val serviceService: ServiceService
): ServiceControllerInterface {

    override fun findServicePaginated(username: String, pageNum: Int, elementSize: Int): ResponseEntity<ServicePageDTO> {
        return ResponseEntity.ok().body(serviceService.getServicePaginated(username, pageNum, elementSize))
    }

    override fun findServices(): ResponseEntity<List<ServiceDTO>> {
        return ResponseEntity.ok().body(serviceService.getServices())
    }

    override fun getServicesByUsernameAndCategory(username: String, category: String): ResponseEntity<List<ServiceDTO>> {
        return ResponseEntity.ok().body(serviceService.getServicesByUsernameAndCategory(username, category))
    }
}