package com.etno.microservice.model.dto.mail

import com.fasterxml.jackson.annotation.JsonProperty

data class MailDetailsDTO(
    @JsonProperty("address") var address: String ? = null,

    @JsonProperty("message") var message: String ? = null,

    @JsonProperty("subject") var subject: String ? = null,

    @JsonProperty("attachment") var attachment: String ? = null
)
