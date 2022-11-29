package com.etno.microservice.util

import com.etno.microservice.model.User

data class TokenUser(
    var error: Boolean? = null,
    var message: String? = null,
    var token: String? = null,
    var token_expired: String? = null,
    var expired_date: String? = null,
    var user: User? = null
)
