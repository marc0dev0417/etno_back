package com.etno.microservice.service

import com.etno.microservice.model.dto.PhoneDTO
import org.springframework.stereotype.Service
import java.awt.print.Pageable

@Service
interface PhoneServiceInterface {
    fun getPhones(): List<PhoneDTO>
    fun getPhonesByUsernameAndCategory(username: String, category: String): List<PhoneDTO>?
}