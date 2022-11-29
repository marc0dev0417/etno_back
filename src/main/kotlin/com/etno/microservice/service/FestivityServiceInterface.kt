package com.etno.microservice.service

import com.etno.microservice.model.dto.FestivityDTO
import org.springframework.stereotype.Service

@Service
interface FestivityServiceInterface {
    fun getFestivities(): List<FestivityDTO>?
}