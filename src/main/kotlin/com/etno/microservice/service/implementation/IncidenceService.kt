package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.IncidentDTO
import com.etno.microservice.model.dto.pagination.IncidentPageDTO
import com.etno.microservice.repository.IncidenceRepository
import com.etno.microservice.service.IncidenceServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.data.domain.PageRequest
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

    override fun getIncidentPaginated(username: String, pageNum: Int, pageSize: Int): IncidentPageDTO? {
        val pageable = PageRequest.of(pageNum, pageSize)
        val pagedResult = incidenceRepository.findIncidentsPageableByUsername(username, pageable)

        return if (pagedResult!!.hasContent()) {
            IncidentPageDTO(
                content = pagedResult.content.toList().map { DataConverter.incidenceToDTO(it) },
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        } else {
            IncidentPageDTO(
                content = emptyList(),
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        }
    }
}