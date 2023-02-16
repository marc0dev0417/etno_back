package com.etno.microservice.service.implementation

import com.etno.microservice.model.Phone
import com.etno.microservice.model.dto.PhoneDTO
import com.etno.microservice.repository.PhoneRepository
import com.etno.microservice.service.PhoneServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.awt.print.Pageable

@Service
class PhoneService(
    private val phoneRepository: PhoneRepository
): PhoneServiceInterface {
    override fun getPhones(): List<PhoneDTO> {
        return phoneRepository.findAll().map { DataConverter.phoneToDTO(it) }
    }

    override fun getPhonesByUsernameAndCategory(username: String, category: String): List<PhoneDTO>? {
        return phoneRepository.findPhoneByUsernameAndCategory(username, category)?.map { DataConverter.phoneToDTO(it) }
    }


}