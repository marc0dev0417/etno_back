package com.etno.microservice.exception.handler

import com.etno.microservice.exception.Constants

data class BadRequestException(
    val code: String = Constants.BAD_REQUEST.code,
    val reason: Constants
): RuntimeException(reason.message)