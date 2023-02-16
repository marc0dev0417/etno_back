package com.etno.microservice.model.dto.pagination

import com.etno.microservice.model.dto.BandoDTO


data class BandoPageDTO(
    var content: List<BandoDTO> ? = emptyList(),
    var totalPages: Int ? = null,
    var totalElements : Long ? = null,
    var pageNum : Int ? = null
)
