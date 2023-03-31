package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class ReserveScheduleDTO(
    @JsonProperty("idReserveSchedule") val idReserveSchedule: UUID ? = UUID.randomUUID(),

    @JsonProperty("date") val date: String ? = null
)