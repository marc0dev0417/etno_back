package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class UserDTO(
    @JsonProperty("id") var id: UUID = UUID.randomUUID(),

    @JsonProperty("username") val username: String? = null,

    @JsonProperty("password") val password: String? = null,

    @JsonProperty("role") val role: String? = null,

    //@JsonProperty("list") val list: MutableList<EventDTO>? = mutableListOf()
)
