package com.etno.microservice.service

import com.etno.microservice.model.dto.AdDTO
import org.springframework.stereotype.Service

@Service
interface AdServiceInterface {
    fun getAds(): List<AdDTO>
    fun getAdsByUsername(username: String): List<AdDTO>
}