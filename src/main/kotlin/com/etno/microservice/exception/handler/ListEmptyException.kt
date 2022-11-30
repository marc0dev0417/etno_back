package com.etno.microservice.exception.handler

import com.etno.microservice.exception.Constants
import java.lang.RuntimeException

data class ListEmptyException(
    val code: String = Constants.LIST_EMPTY.code,
    val reason: Constants
): RuntimeException(reason.message)
