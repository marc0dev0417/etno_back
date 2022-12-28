package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.PhoneDTO
import com.etno.microservice.repository.PhoneRepository
import com.etno.microservice.service.PhoneServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service

@Service
class PhoneService(
    private val phoneRepository: PhoneRepository
): PhoneServiceInterface {
    override fun getPhones(): List<PhoneDTO> {
        return phoneRepository.findAll().map { DataConverter.phoneToDTO(it) }
    }
}