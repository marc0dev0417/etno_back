package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.IncidentDTO
import com.etno.microservice.repository.IncidenceRepository
import com.etno.microservice.service.IncidenceServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service

@Service
class IncidenceService(
    private val incidenceRepository: IncidenceRepository
): IncidenceServiceInterface {
    override fun getIncidences(): List<IncidentDTO> {
        return incidenceRepository.findAll().map { DataConverter.incidenceToDTO(it) }
    }

    override fun findIncidentByUsernameAndFcmToken(username: String, fcmToken: String): List<IncidentDTO>? {
        return incidenceRepository.findIncidentsByUsernameAndFcmToken(username, fcmToken)?.map { DataConverter.incidenceToDTO(it) }
    }
}