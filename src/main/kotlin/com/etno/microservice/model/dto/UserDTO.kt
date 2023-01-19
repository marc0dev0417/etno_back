package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class UserDTO(
    @JsonProperty("idUser") var idUser: UUID? = UUID.randomUUID(),

    @JsonProperty("username") val username: String? = null,

    @JsonProperty("password") val password: String? = null,

    @JsonProperty("events") val events: MutableList<EventDTO>? = mutableListOf(),

    @JsonProperty("pharmacies") val pharmacies: MutableList<PharmacyDTO> ? = mutableListOf(),

    @JsonProperty("tourism") val tourism: MutableList<TourismDTO> ? = mutableListOf(),

    @JsonProperty("deaths") val deaths: MutableList<DeathDTO> ? = mutableListOf(),

    @JsonProperty("phones") val phones: MutableList<PhoneDTO> ? = mutableListOf(),

    @JsonProperty("news") var news: MutableList<NewDTO> ? = mutableListOf(),

    @JsonProperty("incidents") var incidents: MutableList<IncidentDTO> ? = mutableListOf(),

    @JsonProperty("bandos") var bandos: MutableList<BandoDTO> ? = mutableListOf()
)