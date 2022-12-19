package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.PharmacyDTO
import com.etno.microservice.repository.PharmacyRepository
import com.etno.microservice.service.PharmacyServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service

@Service
class PharmacyService(
    private val pharmacyRepository: PharmacyRepository
): PharmacyServiceInterface{
    override fun getPharmacies(): List<PharmacyDTO> {
        return pharmacyRepository.findAll().map { DataConverter.pharmacyToDTO(it) }
    }

    override fun savePharmacy(pharmacyDTO: PharmacyDTO): PharmacyDTO? {
        val itemToSave = pharmacyRepository.save(DataConverter.pharmacyFromDTO(pharmacyDTO))
        return DataConverter.pharmacyToDTO(itemToSave)
    }
}