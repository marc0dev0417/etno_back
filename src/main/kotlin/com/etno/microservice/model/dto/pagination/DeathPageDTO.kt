package com.etno.microservice.model.dto.pagination

import com.etno.microservice.model.dto.DeathDTO


data class DeathPageDTO(
    var content: List<DeathDTO> ? = emptyList(),
    var totalPages: Int ? = null,
    var totalElements : Long ? = null,
    var pageNum : Int ? = null
)
