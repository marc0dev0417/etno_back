package com.etno.microservice.service

import com.etno.microservice.model.dto.ReserveDTO
import org.springframework.stereotype.Service

@Service
interface ReserveServiceInterface {
    fun getReserves(): List<ReserveDTO>?
}