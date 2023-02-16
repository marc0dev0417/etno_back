package com.etno.microservice.service

import com.etno.microservice.model.dto.DeathDTO
import com.etno.microservice.model.dto.pagination.DeathPageDTO
import org.springframework.stereotype.Service

@Service
interface DeathServiceInterface {
    fun getDeaths(): List<DeathDTO>?
    fun getDeathsByUsername(username: String): List<DeathDTO>?
    fun getDeathPaginated(username: String, pageNum: Int, pageSize: Int): DeathPageDTO?
}