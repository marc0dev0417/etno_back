package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class PharmacyDTO(
    @JsonProperty("idPharmacy") var idPharmacy: UUID ? = null,

    @JsonProperty("username") var username: String ? = null,

    @JsonProperty("type") var type: String ? = null,

    @JsonProperty("name") var name: String ? = null,

    @JsonProperty("link") var link: String ? = null,

    @JsonProperty("imageUrl") var imageUrl: String ? = null,

    @JsonProperty("phone") var phone: String ? = null,

    @JsonProperty("schedule") var schedule: String ? = null,

    @JsonProperty("direction") var direction: String ? = null,

    @JsonProperty("latitude") var longitude: Double ? = null,

    @JsonProperty("longitude") var latitude: Double ? = null,

    @JsonProperty("startDate") var startDate: Date ? = null,

    @JsonProperty("durationDays") var durationDays: Int ? = null,

    @JsonProperty("frequencyInDays") var frequencyInDays: Int ? = null,

    @JsonProperty("dates") var dates: MutableList<PharmacyDateDTO>? = mutableListOf()
)