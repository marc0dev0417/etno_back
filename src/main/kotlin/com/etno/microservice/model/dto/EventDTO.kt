package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class EventDTO(
    @JsonProperty("idEvent") var idEvent: UUID? = UUID.randomUUID(),

    @JsonProperty("title") var title: String? = null,

)
