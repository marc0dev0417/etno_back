package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.ReserveDTO
import com.etno.microservice.model.dto.pagination.ReservePageDTO
import com.etno.microservice.repository.ReserveRepository
import com.etno.microservice.service.ReserveServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ReserveService(
    private val reserveRepository: ReserveRepository
): ReserveServiceInterface {
    override fun getReserves(): List<ReserveDTO>? {
        return reserveRepository.findAll().map { DataConverter.reserveToDTO(it) }
    }

    override fun getReservesByUsername(username: String): List<ReserveDTO> {
        return reserveRepository.getReservesByUsername(username)!!.map { DataConverter.reserveToDTO(it) }
    }

    override fun getReservesPaginated(username: String, pageNum: Int, pageSize: Int): ReservePageDTO? {
        val pageable = PageRequest.of(pageNum, pageSize)
        val pagedResult = reserveRepository.findReservePageableByUsername(username, pageable)

        return if (pagedResult!!.hasContent()){
            ReservePageDTO(
                content = pagedResult.content.toList().map { DataConverter.reserveToDTO(it) },
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        } else {
            ReservePageDTO(
                content = emptyList(),
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        }
    }
}