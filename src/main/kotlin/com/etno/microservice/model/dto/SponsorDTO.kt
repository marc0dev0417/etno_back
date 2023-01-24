package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class SponsorDTO(
    @JsonProperty("idSponsor") var idSponsor: UUID? = null,

    @JsonProperty("username") var username: String ? = null,

    @JsonProperty("title") var title: String ? = null,

    @JsonProperty("description") var description: String ? = null,

    @JsonProperty("phone") var phone: String ? = null,

    @JsonProperty("urlImage") var urlImage: String ? = null
)