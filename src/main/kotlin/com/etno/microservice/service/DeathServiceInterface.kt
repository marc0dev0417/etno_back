package com.etno.microservice.service

import com.etno.microservice.model.dto.DeathDTO
import org.springframework.stereotype.Service

@Service
interface DeathServiceInterface {
    fun getDeaths(): List<DeathDTO>?
}