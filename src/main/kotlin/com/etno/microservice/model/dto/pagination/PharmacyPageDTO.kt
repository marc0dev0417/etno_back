package com.etno.microservice.model.dto.pagination


import com.etno.microservice.model.dto.PharmacyDTO

data class PharmacyPageDTO(
    var content: List<PharmacyDTO> ? = emptyList(),
    var totalPages: Int ? = null,
    var totalElements : Long ? = null,
    var pageNum : Int ? = null
)
