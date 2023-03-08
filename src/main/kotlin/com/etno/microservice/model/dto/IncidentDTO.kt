package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class IncidentDTO(
    @JsonProperty("idIncident") var idIncidence: UUID? = UUID.randomUUID(),

    @JsonProperty("username") var username: String ? = null,

    @JsonProperty("fcmToken") var fcmToken: String ? = null,

    @JsonProperty("title") var title: String ? = null,

    @JsonProperty("description") var description: String ? = null,

    @JsonProperty("isSolved") var isSolved: Boolean ? = null,

    @JsonProperty("solution") var solution: String ? = null
)
