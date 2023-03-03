package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.PlaceDTO
import com.etno.microservice.repository.PlaceRepository
import com.etno.microservice.service.PlaceServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service

@Service
class PlaceService(
    private val placeRepository: PlaceRepository
): PlaceServiceInterface {
    override fun getPlaces(): List<PlaceDTO>? {
        return placeRepository.findAll().map { DataConverter.placeToDTO(it) }
    }
}