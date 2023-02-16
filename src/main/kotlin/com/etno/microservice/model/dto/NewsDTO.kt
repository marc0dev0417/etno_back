package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class NewsDTO(
    @JsonProperty("idNew") var idNew: UUID ? = UUID.randomUUID(),

    @JsonProperty("username") var username: String ? = null,

    @JsonProperty("category") var category: String ? = null,

    @JsonProperty("title") var title: String ? = null,

    @JsonProperty("publicationDate") var publicationDate: String ? = null,

    @JsonProperty("description") var description: String ? = null,

    @JsonProperty("imageUrl") var imageUrl: String ? = null
)