package com.etno.microservice.model.dto.pagination

import com.etno.microservice.model.dto.CustomLinkDTO

data class CustomLinkPageDTO(
    var content: List<CustomLinkDTO>? = mutableListOf(),
    var totalPages: Int? = null,
    var totalElements: Long? = null,
    var pageNum: Int? = null
)