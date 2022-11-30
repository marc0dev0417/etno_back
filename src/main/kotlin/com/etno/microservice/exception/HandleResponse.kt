package com.etno.microservice.exception

import com.fasterxml.jackson.annotation.JsonProperty

data class HandleResponse(
    @JsonProperty("code") val code: String? = null,

    @JsonProperty("message") val errorCode: String? = null,

)
