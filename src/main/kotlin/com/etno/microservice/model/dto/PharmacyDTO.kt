package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class PharmacyDTO(
    @JsonProperty("idPharmacy") var idPharmacy: UUID ? = null,

    @JsonProperty("type") var type: String ? = null,

    @JsonProperty("name") var name: String ? = null,

    @JsonProperty("phone") var phone: String ? = null,

    @JsonProperty("schedule") var schedule: String ? = null,

    @JsonProperty("longitude") var longitude: String ? = null,

    @JsonProperty("latitude") var latitude: String ? = null
)