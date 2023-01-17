package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.IncidenceControllerInterface
import com.etno.microservice.model.dto.IncidentDTO
import com.etno.microservice.service.implementation.IncidenceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class IncidenceController(
    private val incidenceService: IncidenceService
): IncidenceControllerInterface {
    override fun getIncidences(): ResponseEntity<List<IncidentDTO>> {
        return ResponseEntity.ok().body(incidenceService.getIncidences())
    }

    override fun getIncidentsByUsernameAndFcmToken(
        username: String,
        fcmToken: String
    ): ResponseEntity<List<IncidentDTO>> {
        return ResponseEntity.ok().body(incidenceService.findIncidentByUsernameAndFcmToken(username, fcmToken))
    }
}