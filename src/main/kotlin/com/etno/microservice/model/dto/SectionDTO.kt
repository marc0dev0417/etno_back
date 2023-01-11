package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class SectionDTO(
    @JsonProperty("idSection") var idSection: UUID ? = UUID.randomUUID(),

    @JsonProperty("username") var username: String ? = null,

    @JsonProperty("category") var category: String ? = null,

    @JsonProperty("title") var title: String ? = null,
)