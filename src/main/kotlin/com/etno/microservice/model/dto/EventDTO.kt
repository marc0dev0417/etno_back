package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class EventDTO(
    @JsonProperty("idEvent") var idEvent: UUID? = UUID.randomUUID(),

    @JsonProperty("title") var title: String? = null,

    @JsonProperty("description") var description: String? = null,

    @JsonProperty("startDate") var startDate: Date? = null,

    @JsonProperty("endDate") var endDate: Date? = null,

    @JsonProperty("publicationDate") var publicationDate: Date? = null,

    @JsonProperty("lat") var latitude: String? = null,

    @JsonProperty("long") var longitude: String? = null,

    @JsonProperty("subscription") var subscription: Boolean? = null,

    @JsonProperty("images") var images: MutableList<ImageDTO>? = mutableListOf(),

    @JsonProperty("videos") var videos: MutableList<VideoDTO>? = mutableListOf()
)
