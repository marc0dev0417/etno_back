package com.etno.microservice.service.implementation


import com.etno.microservice.model.dto.ServiceDTO
import com.etno.microservice.model.dto.pagination.ServicePageDTO
import com.etno.microservice.repository.ServiceRepository
import com.etno.microservice.service.ServiceServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ServiceService(
    private val serviceRepository: ServiceRepository
): ServiceServiceInterface {
    override fun getServices(): List<ServiceDTO> {
        return serviceRepository.findAll().map { DataConverter.serviceToDTO(it) }
    }

    override fun getServicesByUsernameAndCategory(username: String, category: String): List<ServiceDTO>? {
        return serviceRepository.findServiceByUsernameAndCategory(username, category)?.map { DataConverter.serviceToDTO(it) }
    }

    override fun getServicePaginated(username: String, pageNum: Int, pageSize: Int): ServicePageDTO? {
        val pageable = PageRequest.of(pageNum, pageSize)
        val pagedResult = serviceRepository.findServicePageableByUsername(username, pageable)
        return if (pagedResult!!.hasContent()){
            ServicePageDTO(
                content = pagedResult.content.toList().map { DataConverter.serviceToDTO(it) },
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        }else{
            ServicePageDTO(
                content = emptyList(),
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        }
    }
}