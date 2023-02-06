package com.etno.microservice.model.dto.pagination

import com.etno.microservice.model.dto.EventDTO

data class EventPageDTO(
    var content: List<EventDTO>? = mutableListOf(),
    var totalPages: Int? = null,
    var totalElements: Long? = null,
    var pageNum: Int? = null
)
