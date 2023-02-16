package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.DeathDTO
import com.etno.microservice.model.dto.pagination.DeathPageDTO
import com.etno.microservice.repository.DeathRepository
import com.etno.microservice.service.DeathServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class DeathService(
    private val deathRepository: DeathRepository
): DeathServiceInterface {
    override fun getDeaths(): List<DeathDTO>? {
        return deathRepository.findAll().map { DataConverter.deathToDTO(it) }
    }

    override fun getDeathsByUsername(username: String): List<DeathDTO>? {
        return deathRepository.findDeathsByUsername(username)?.map { DataConverter.deathToDTO(it) }
    }

    override fun getDeathPaginated(username: String, pageNum: Int, pageSize: Int): DeathPageDTO? {
        val pageable = PageRequest.of(pageNum, pageSize)
        val pagedResult = deathRepository.findDeathPageableByUsername(username, pageable)
        return if (pagedResult!!.hasContent()){
            DeathPageDTO(
                content = pagedResult.content.toList().map { DataConverter.deathToDTO(it) },
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        } else {
            DeathPageDTO(
                content = emptyList(),
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        }
    }
}