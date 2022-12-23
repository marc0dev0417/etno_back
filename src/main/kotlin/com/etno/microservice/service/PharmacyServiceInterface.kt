package com.etno.microservice.service

import com.etno.microservice.model.dto.PharmacyDTO
import org.springframework.stereotype.Service

@Service
interface PharmacyServiceInterface {
    fun getPharmacies(): List<PharmacyDTO>
    fun savePharmacy(pharmacyDTO: PharmacyDTO): PharmacyDTO?
}