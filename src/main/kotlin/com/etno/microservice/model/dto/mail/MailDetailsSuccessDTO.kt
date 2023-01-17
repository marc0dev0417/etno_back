package com.etno.microservice.model.dto.mail

import com.fasterxml.jackson.annotation.JsonProperty

data class MailDetailsSuccessDTO(
    @JsonProperty("message") var message: String ? = null
)
