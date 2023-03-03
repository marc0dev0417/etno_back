package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class ReserveDTO(
    @JsonProperty("idReserve") var idReserve: UUID ? = UUID.randomUUID(),

    @JsonProperty("username") var username: String ? = null,

    @JsonProperty("name") val name: String ? = null,

    @JsonProperty("email") val email: String ? = null,

    @JsonProperty("phone") val phone: String ? = null,

    @JsonProperty("isPrivate") val isPrivate: Boolean ? = null,

    @JsonProperty("place") val place: PlaceDTO ? = null,

    @JsonProperty("hall") val hall: String ? = null,

    @JsonProperty("date") val date: String ? = null
)
