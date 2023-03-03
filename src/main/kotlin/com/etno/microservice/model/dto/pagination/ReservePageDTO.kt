package com.etno.microservice.model.dto.pagination
import com.etno.microservice.model.dto.ReserveDTO

data class ReservePageDTO(
    var content: List<ReserveDTO> ? = emptyList(),
    var totalPages: Int ? = null,
    var totalElements: Long ? = null,
    var pageNum: Int ? = null
)