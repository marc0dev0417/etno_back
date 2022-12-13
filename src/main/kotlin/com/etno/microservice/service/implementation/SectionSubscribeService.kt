package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.SectionSubscribeDTO
import com.etno.microservice.repository.SectionSubscribeRepository
import com.etno.microservice.service.SectionSubscribeInterface
import com.etno.microservice.util.DataConverter

class SectionSubscribeService(
    private val sectionSubscribeRepository: SectionSubscribeRepository
): SectionSubscribeInterface {
    override fun getSectionSubscribe(): List<SectionSubscribeDTO> {
        return sectionSubscribeRepository.findAll().map { DataConverter.sectionSubscribeToDTO(it) }
    }
}