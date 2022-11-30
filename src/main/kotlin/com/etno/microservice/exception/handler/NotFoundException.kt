package com.etno.microservice.exception.handler

import com.etno.microservice.exception.Constants

data class NotFoundException(
    val code: String = Constants.NOT_FOUND.code,
    val reason: Constants
): RuntimeException(reason.message)
