package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class LinkDTO(
    @JsonProperty("idLink") var idLink: UUID? = null,

    @JsonProperty("name") var name: String ? = null,

    @JsonProperty("url") var url: String ? = null
)