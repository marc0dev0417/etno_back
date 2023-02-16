package com.etno.microservice.service

import com.etno.microservice.model.dto.PharmacyDTO

import com.etno.microservice.model.dto.pagination.PharmacyPageDTO
import org.springframework.stereotype.Service

@Service
interface PharmacyServiceInterface {
    fun getPharmacies(): List<PharmacyDTO>
    fun savePharmacy(pharmacyDTO: PharmacyDTO): PharmacyDTO?
    fun getPharmaciesByUsername(username: String): List<PharmacyDTO>?
    fun getPharmacyPaginated(username: String, pageNum: Int, pageSize: Int): PharmacyPageDTO?
}