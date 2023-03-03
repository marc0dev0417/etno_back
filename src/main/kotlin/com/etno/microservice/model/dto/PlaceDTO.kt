package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class PlaceDTO(
    @JsonProperty("idPlace") var idPlace: UUID? = null,

    @JsonProperty("username") val username: String ? = null,

    @JsonProperty("placeName") val placeName: String ? = null,

    @JsonProperty("latitude") val latitude: Double ? = null,

    @JsonProperty("longitude") val longitude: Double ? = null
)