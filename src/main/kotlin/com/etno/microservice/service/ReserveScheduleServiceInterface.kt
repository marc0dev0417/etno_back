package com.etno.microservice.service

import com.etno.microservice.model.dto.ReserveScheduleDTO
import org.springframework.stereotype.Service

@Service
interface ReserveScheduleServiceInterface {
    fun getReserveSchedules(): List<ReserveScheduleDTO>
}