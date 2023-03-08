package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class PlaceDTO(
    @JsonProperty("idPlace") var idPlace: UUID? = null,

    @JsonProperty("username") var username: String ? = null,

    @JsonProperty("name") val name: String ? = null,

    @JsonProperty("latitude") val latitude: Double ? = null,

    @JsonProperty("longitude") val longitude: Double ? = null,

    @JsonProperty("halls") val halls: MutableList<HallDTO> ? = null
)