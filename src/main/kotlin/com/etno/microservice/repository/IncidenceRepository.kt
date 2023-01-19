package com.etno.microservice.repository

import com.etno.microservice.model.FCMToken
import com.etno.microservice.model.Incident
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IncidenceRepository: JpaRepository<Incident, UUID> {
    fun findIncidentByUsernameAndTitle(username: String, title: String): Incident?
    fun findIncidentsByUsernameAndFcmToken(username: String, fcmToken: String): List<Incident>?
}