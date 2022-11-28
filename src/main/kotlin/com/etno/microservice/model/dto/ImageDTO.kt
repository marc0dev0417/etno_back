package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class ImageDTO(
    @JsonProperty("idImage") var idImage: UUID? = UUID.randomUUID(),

    @JsonProperty("name") var name: String? = null,

    @JsonProperty("link") var link: String? = null
)