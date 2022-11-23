package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class UserDTO(
    @JsonProperty("idUser") var idUser: UUID? = UUID.randomUUID(),

    @JsonProperty("username") val username: String? = null,

    @JsonProperty("password") val password: String? = null,

    @JsonProperty("role") val role: String? = null,

    @JsonProperty("events") val events: MutableList<EventDTO>? = mutableListOf()
)