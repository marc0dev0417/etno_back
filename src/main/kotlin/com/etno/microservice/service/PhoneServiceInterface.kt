package com.etno.microservice.service

import com.etno.microservice.model.Phone
import com.etno.microservice.model.dto.PhoneDTO
import org.springframework.stereotype.Service

@Service
interface PhoneServiceInterface {
    fun getPhones(): List<PhoneDTO>
}