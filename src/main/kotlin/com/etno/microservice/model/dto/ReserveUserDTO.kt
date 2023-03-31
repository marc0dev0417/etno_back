package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class ReserveUserDTO(
    @JsonProperty("idReserveUser") var idReserveUser: UUID ? = UUID.randomUUID(),

    @JsonProperty("fcmToken") var fcmToken: String ? = null,

    @JsonProperty("data") var data: String ? = null,

    @JsonProperty("place") var place: PlaceDTO ? = null,

    @JsonProperty("isReserved") var isReserved: Boolean ? = null,

    @JsonProperty("description") var description: String ? = null,

    @JsonProperty("reservePhone") var reservePhone: String ? = null,

    @JsonProperty("latitude") var latitude: Double ? = null,

    @JsonProperty("longitude") var longitude: Double ? = null,

    @JsonProperty("date") var date: String ? = null,

    @JsonProperty("reserveSchedules") var reserveSchedules: MutableList<ReserveScheduleDTO> ? = mutableListOf()
)