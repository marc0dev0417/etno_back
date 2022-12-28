package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class FCMTokenDTO(
    @JsonProperty("idFMC") var idFMC: UUID? = UUID.randomUUID(),

    @JsonProperty("locality") var username: String ? = null,

    @JsonProperty("token") var token: String? = null
)