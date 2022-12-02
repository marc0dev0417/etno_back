package com.etno.microservice.model.dto.firebase

data class NoteDTO(
    var subject: String? = null,
    var content: String? = null,
    var data: Map<String, String>? = emptyMap(),
    var image: String? = null
)
