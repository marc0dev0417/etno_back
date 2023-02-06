package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class AdDTO(
    @JsonProperty("idAd") var idAd: UUID ? = null,

    @JsonProperty("username") var username: String ? = null,

    @JsonProperty("title") var title: String ? = null,

    @JsonProperty("description") var description: String ? = null,

    @JsonProperty("imageUrl") var imageUrl: String ? = null,

    @JsonProperty("webUrl") var webUrl: String ? = null
)