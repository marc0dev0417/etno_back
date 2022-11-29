package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.FestivityDTO
import com.etno.microservice.repository.FestivityRepository
import com.etno.microservice.service.FestivityServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service

@Service
class FestivityService(
    private val festivityRepository: FestivityRepository
): FestivityServiceInterface {
    override fun getFestivities(): List<FestivityDTO>? {
        return festivityRepository.findAll().map { DataConverter.festivityToDTO(it) }
    }
}