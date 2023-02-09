package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.AdDTO
import com.etno.microservice.model.dto.pagination.AdPageDTO
import com.etno.microservice.repository.AdRepository
import com.etno.microservice.service.AdServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AdService(
    private val adRepository: AdRepository
): AdServiceInterface {
    override fun getAds(): List<AdDTO> {
        return adRepository.findAll().map { DataConverter.adToDTO(it) }
    }

    override fun getAdsByUsername(username: String): List<AdDTO> {
        return adRepository.findAdsByUsername(username)!!.map { DataConverter.adToDTO(it) }
    }

    override fun getAdsPaginated(username: String, pageNum: Int, pageSize: Int): AdPageDTO? {
        val pageable = PageRequest.of(pageNum, pageSize)
        val pagedResult = adRepository.findAdsPageableByUsername(username, pageable)

        return if (pagedResult!!.hasContent()){
            AdPageDTO(
                    content = pagedResult.content.toList().map { DataConverter.adToDTO(it) },
                    totalElements = pagedResult.totalElements,
                    totalPages = pagedResult.totalPages,
                    pageNum = pagedResult.number
            )
        } else {
            AdPageDTO(
                    content = emptyList(),
                    totalElements = pagedResult.totalElements,
                    totalPages = pagedResult.totalPages,
                    pageNum = pagedResult.number
            )
        }
    }
}