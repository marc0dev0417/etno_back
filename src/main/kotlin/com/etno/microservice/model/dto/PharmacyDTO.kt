package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class PharmacyDTO(
    @JsonProperty("idPharmacy") var idPharmacy: UUID ? = null,

    @JsonProperty("username") var username: String ? = null,

    @JsonProperty("type") var type: String ? = null,

    @JsonProperty("name") var name: String ? = null,

    @JsonProperty("link") var link: String ? = null,

    @JsonProperty("imageUrl") var imageUrl: String ? = null,

    @JsonProperty("phone") var phone: String ? = null,

    @JsonProperty("schedule") var schedule: String ? = null,

    @JsonProperty("description") var description: String ? = null,

    @JsonProperty("latitude") var longitude: String ? = null,

    @JsonProperty("longitude") var latitude: String ? = null
)