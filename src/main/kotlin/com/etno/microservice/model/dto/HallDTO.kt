package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class HallDTO(
    @JsonProperty("idHall") val idHall: UUID? = null,

    @JsonProperty("username") val username: String ? = null,

    @JsonProperty("name") val name: String ? = null
    )