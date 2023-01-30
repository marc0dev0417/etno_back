package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class UserLoginDTO(
    @JsonProperty("idUser") var idUser: UUID ? = null,
    @JsonProperty("username") var username: String ? = null,
    @JsonProperty("password") var password: String ? = null
)
