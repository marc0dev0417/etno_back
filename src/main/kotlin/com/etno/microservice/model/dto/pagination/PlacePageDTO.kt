package com.etno.microservice.model.dto.pagination

import com.etno.microservice.model.dto.PlaceDTO

data class PlacePageDTO(
    var content: List<PlaceDTO> = emptyList(),
    var totalPages: Int ? = null,
    var totalElements: Long ? = null,
    var pageNum: Int ? = null
)