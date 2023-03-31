package com.etno.microservice.service

import com.etno.microservice.model.dto.HallDTO
import org.springframework.stereotype.Service

@Service
interface HallServiceInterface {
    fun getHalls(): List<HallDTO>?
    fun saveHall(hallDTO: HallDTO): HallDTO?
}