package com.etno.microservice.service

import com.etno.microservice.model.dto.ReserveDTO
import com.etno.microservice.model.dto.pagination.ReservePageDTO
import org.springframework.stereotype.Service

@Service
interface ReserveServiceInterface {
    fun getReserves(): List<ReserveDTO>?
    fun getReservesByUsername(username: String): List<ReserveDTO>
    fun getReservesPaginated(username: String, pageNum: Int, pageSize: Int): ReservePageDTO?
}