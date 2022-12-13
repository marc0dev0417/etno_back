package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class SectionSubscribeDTO(
    @JsonProperty("idSectionSubscribe") var idSectionSubscribe: UUID ? = UUID.randomUUID(),

    @JsonProperty("title") var title: String ? = null,

    @JsonProperty("isSubscribe") var isSubscribe: String ? = null
)