package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class SubscriptionDTO(
    @JsonProperty("idSubscription") var idSubscription: UUID? = UUID.randomUUID(),

    @JsonProperty("category") var category: String ? = null,

    @JsonProperty("title") var title: String ? = null,

    @JsonProperty("isSubscribe") var isSubscribe: Boolean ? = null,

    @JsonProperty("subscriptionUsers") var subscriptionUsers: MutableList<SubscriptionUserDTO> = mutableListOf()
)