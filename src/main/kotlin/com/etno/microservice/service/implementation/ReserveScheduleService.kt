package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.ReserveScheduleDTO
import com.etno.microservice.repository.ReserveScheduleRepository
import com.etno.microservice.service.ReserveScheduleServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service

@Service
class ReserveScheduleService(
    private val reserveScheduleRepository: ReserveScheduleRepository
): ReserveScheduleServiceInterface {
    override fun getReserveSchedules(): List<ReserveScheduleDTO> {
        return reserveScheduleRepository.findAll().map { DataConverter.reserveScheduleToDTO(it) }
    }
}