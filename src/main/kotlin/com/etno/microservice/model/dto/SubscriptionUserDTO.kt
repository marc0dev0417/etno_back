package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class SubscriptionUserDTO(
    @JsonProperty("idSubscriptionUser") var idSubscriptionUser: UUID ? = UUID.randomUUID(),

    @JsonProperty("fcmToken") var fcmToken: String ? = null,

    @JsonProperty("title") var title: String ? = null,

    @JsonProperty("seats") var seats: Int ? = null,

    @JsonProperty("name") var name: String ? = null,

    @JsonProperty("mail") var mail: String ? = null,

    @JsonProperty("phone") var phone: String ? = null,

    @JsonProperty("wallet") var wallet: Double ? = null,

    @JsonProperty("isSubscribe") var isSubscribe: Boolean ? = null
)
