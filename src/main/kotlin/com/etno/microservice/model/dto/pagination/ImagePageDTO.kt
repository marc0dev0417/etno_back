package com.etno.microservice.model.dto.pagination

import com.etno.microservice.model.dto.ImageDTO

data class ImagePageDTO(
    var content: List<ImageDTO> ? = emptyList(),
    var totalPages: Int ? = null,
    var totalElements: Long ? = null,
    var pageNum: Int ? = null
)