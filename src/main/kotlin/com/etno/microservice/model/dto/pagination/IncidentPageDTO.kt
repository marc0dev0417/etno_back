package com.etno.microservice.model.dto.pagination

import com.etno.microservice.model.dto.IncidentDTO

data class IncidentPageDTO(
    var content: List<IncidentDTO> ? = emptyList(),
    var totalPages: Int ? = null,
    var totalElements: Long ? = null,
    var pageNum: Int ? = null
)