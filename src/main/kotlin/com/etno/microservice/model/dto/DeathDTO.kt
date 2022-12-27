package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class DeathDTO(
    @JsonProperty("idDeath") var idDeath: UUID ? = UUID.randomUUID(),

    @JsonProperty("username") var username: String ? = null,

    @JsonProperty("name") var name: String ? = null,

    @JsonProperty("deathDate") var deathDate: String ? = null,

    @JsonProperty("description") var description: String ? = null
)
