package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class EventDTO(
    @JsonProperty("idEvent") var idEvent: UUID? = UUID.randomUUID(),

    @JsonProperty("username") var username: String ? = null,

    @JsonProperty("title") var title: String? = null,

    @JsonProperty("address") var address: String? = null,

    @JsonProperty("description") var description: String? = null,

    @JsonProperty("organization") var organization: String? = null,

    @JsonProperty("reservePrice") var reservePrice: Double ? = null,

    @JsonProperty("seats") var seats: Int ? = null,

    @JsonProperty("capacity") var capacity: Int ? = null,

    @JsonProperty("link") var link: String? = null,

    @JsonProperty("imageUrl") var imageUrl: String ? = null,

    @JsonProperty("startDate") var startDate: String? = null,

    @JsonProperty("endDate") var endDate: String? = null,

    @JsonProperty("publicationDate") var publicationDate: String? = null,

    @JsonProperty("time") var time: String? = null,

    @JsonProperty("lat") var lat: String? = null,

    @JsonProperty("long") var long: String? = null,

    @JsonProperty("images") var images: MutableList<ImageDTO>? = mutableListOf(),

    @JsonProperty("videos") var videos: MutableList<VideoDTO>? = mutableListOf()
)
