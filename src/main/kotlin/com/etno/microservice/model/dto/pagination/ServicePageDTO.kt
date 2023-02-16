package com.etno.microservice.model.dto.pagination


import com.etno.microservice.model.dto.ServiceDTO

data class ServicePageDTO(
    var content: List<ServiceDTO> ? = emptyList(),
    var totalPages: Int ? = null,
    var totalElements : Long ? = null,
    var pageNum : Int ? = null
)
