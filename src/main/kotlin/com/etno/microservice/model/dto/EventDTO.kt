package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class EventDTO(
    @JsonProperty("id") var id: UUID = UUID.randomUUID(),

    @JsonProperty("title") var title: String? = null,

    @JsonProperty("description") var description: String? = null,

    @JsonProperty("organization") var organization: String? = null,

    @JsonProperty("video_url") var videoUrl: String? = null,

    @JsonProperty("link_extern") var linkExtern: String? = null,

    @JsonProperty("date_event") var dateEvent: String? = null,

    @JsonProperty("publication_date") var publicationDate: String? = null,

    @JsonProperty("descriptive_place") var descriptivePlace: String? = null,

    @JsonProperty("lat") var latitude: String? = null,

    @JsonProperty("long") var longitude: String? = null,

    @JsonProperty("subscription") var subscription: Boolean? = null
)
