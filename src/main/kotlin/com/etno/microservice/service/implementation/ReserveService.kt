package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.ReserveDTO
import com.etno.microservice.repository.ReserveRepository
import com.etno.microservice.service.ReserveServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service

@Service
class ReserveService(
    private val reserveRepository: ReserveRepository
): ReserveServiceInterface {
    override fun getReserves(): List<ReserveDTO>? {
        return reserveRepository.findAll().map { DataConverter.reserveToDTO(it) }
    }
}