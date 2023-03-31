package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.CustomLinkDTO
import com.etno.microservice.model.dto.pagination.CustomLinkPageDTO
import com.etno.microservice.repository.CustomLinkRepository
import com.etno.microservice.service.CustomLinkServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CustomLinkService(
    private val customLinkRepository: CustomLinkRepository
): CustomLinkServiceInterface{
    override fun getCustomLinks(): List<CustomLinkDTO>? {
        return customLinkRepository.findAll().map { DataConverter.customLinkToDTO(it) }
    }

    override fun getCustomLinksByLocality(username: String): List<CustomLinkDTO>? {
        return customLinkRepository.findCustomLinkByUsername(username)?.map { DataConverter.customLinkToDTO(it) }
    }

    override fun getCustomLinkPaginated(username: String, pageNum: Int, pageSize: Int): CustomLinkPageDTO? {
        val pageable = PageRequest.of(pageNum, pageSize)
        val pagedResult = customLinkRepository.findCustomLinkPageableByUsername(username, pageable)
        return if (pagedResult!!.hasContent()){
            CustomLinkPageDTO(
                content = pagedResult.content.toList().map { DataConverter.customLinkToDTO(it) },
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        }else{
            CustomLinkPageDTO(
                content = emptyList(),
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        }
    }
}