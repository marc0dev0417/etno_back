package com.etno.microservice.exception

enum class Constants(val code: String, val message: String) {
    NOT_FOUND("NOT_FOUND", "ERROR: Content not found"),
    LIST_EMPTY("LIST_EMPTY", "List Empty []"),
    BAD_REQUEST("BAD_REQUEST", "Server cannot process the request, malformed request syntax")
}