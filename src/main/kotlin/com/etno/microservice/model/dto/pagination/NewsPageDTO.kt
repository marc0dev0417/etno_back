package com.etno.microservice.model.dto.pagination

import com.etno.microservice.model.dto.NewsDTO

data class NewsPageDTO(
        var content: List<NewsDTO> = emptyList(),
        var totalPages: Int ? = null,
        var totalElements: Long ? = null,
        var pageNum: Int ? = null
)