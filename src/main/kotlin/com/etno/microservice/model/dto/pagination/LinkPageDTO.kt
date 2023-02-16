package com.etno.microservice.model.dto.pagination


import com.etno.microservice.model.dto.LinkDTO

data class LinkPageDTO(
    var content: List<LinkDTO>? = mutableListOf(),
    var totalPages: Int? = null,
    var totalElements: Long? = null,
    var pageNum: Int? = null
)
