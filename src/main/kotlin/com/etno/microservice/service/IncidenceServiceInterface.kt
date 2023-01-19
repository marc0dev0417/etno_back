package com.etno.microservice.service

import com.etno.microservice.model.dto.IncidentDTO
import org.springframework.stereotype.Service

@Service
interface IncidenceServiceInterface {
    fun getIncidences(): List<IncidentDTO>
    fun findIncidentByUsernameAndFcmToken(username: String, fcmToken: String): List<IncidentDTO>?
}