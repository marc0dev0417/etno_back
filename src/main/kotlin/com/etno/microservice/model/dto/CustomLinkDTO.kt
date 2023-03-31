package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class CustomLinkDTO(
    @JsonProperty("idCustomLink") var idCustomLink: UUID? = UUID.randomUUID(),

    @JsonProperty("username") var username: String ? = null,

    @JsonProperty("name") var name: String ? = null,

    @JsonProperty("webUrl") var webUrl: String ? = null,

    @JsonProperty("iconName") var iconName: String ? = null
)
