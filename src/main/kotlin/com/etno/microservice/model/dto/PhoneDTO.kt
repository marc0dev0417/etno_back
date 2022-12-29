package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class PhoneDTO(
    @JsonProperty("idPhone") var idPhone: UUID ? = UUID.randomUUID(),

    @JsonProperty("username") var username: String ? = null,

    @JsonProperty("category") var category: String ? = null,

    @JsonProperty("owner") var owner: String ? = null,

    @JsonProperty("number") var number: String ? = null,

    @JsonProperty("imageUrl") var imageUrl: String ? = null
)
