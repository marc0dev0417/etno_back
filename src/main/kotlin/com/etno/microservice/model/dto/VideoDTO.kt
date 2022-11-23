package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class VideoDTO(
    @JsonProperty("idVideo") var idVideo: UUID = UUID.randomUUID(),

    @JsonProperty("link") var link: String? = null
)
