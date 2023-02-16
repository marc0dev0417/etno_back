package com.etno.microservice.service

import com.etno.microservice.model.dto.ServiceDTO
import com.etno.microservice.model.dto.pagination.ServicePageDTO
import org.springframework.stereotype.Service

@Service
interface ServiceServiceInterface {
    fun getServices(): List<ServiceDTO>
    fun getServicesByUsernameAndCategory(username: String, category: String): List<ServiceDTO>?
    fun getServicePaginated(username: String, pageNum: Int, pageSize: Int):ServicePageDTO?
}