package com.etno.microservice.model.dto.pagination

import com.etno.microservice.model.dto.TourismDTO

data class TourismPageDTO(
    var content: List<TourismDTO> ? = emptyList(),
    var totalPages: Int ? = null,
    var totalElements : Long ? = null,
    var pageNum : Int ? = null

)
