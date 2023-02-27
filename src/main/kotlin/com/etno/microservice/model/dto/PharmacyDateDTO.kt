package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class PharmacyDateDTO(
    @JsonProperty("idPharmacyDate") var idPharmacyDate: UUID? = null,

    @JsonProperty("username") var username: String ? = null,

    @JsonProperty("namePharmacy") var namePharmacy: String ? = null,

    @JsonProperty("date") var date: Date ? = null
    )
