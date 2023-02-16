package com.etno.microservice.model.dto.pagination


import com.etno.microservice.model.dto.SponsorDTO

data class SponsorPageDTO(
    var content: List<SponsorDTO> ? = emptyList(),
    var totalPages: Int ? = null,
    var totalElements : Long ? = null,
    var pageNum : Int ? = null
)
