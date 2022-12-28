package com.etno.microservice.model.dto.firebase

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class NotificationDTO(
    @JsonProperty("idNotification") var idNotification: UUID = UUID.randomUUID(),

    @JsonProperty("username") var username: String ? = null,

   @JsonProperty("subject") var subject: String? = null,

   @JsonProperty("content") var content: String? = null,

   @JsonProperty("imageUrl") var imageUrl: String? = null
)
