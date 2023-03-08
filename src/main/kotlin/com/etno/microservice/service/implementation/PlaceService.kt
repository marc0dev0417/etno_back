package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.PlaceDTO
import com.etno.microservice.repository.PlaceRepository
import com.etno.microservice.service.PlaceServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlaceService(
    private val placeRepository: PlaceRepository
): PlaceServiceInterface {
    override fun getPlaces(): List<PlaceDTO>? {
        return placeRepository.findAll().map { DataConverter.placeToDTO(it) }
    }

    override fun savePlace(placeDTO: PlaceDTO): PlaceDTO? {
        val placeItem = DataConverter.placeFromDTO(placeDTO)
        placeItem.idPlace = UUID.randomUUID()
        val itemToSave = placeRepository.save(placeItem)
        return DataConverter.placeToDTO(itemToSave)
    }

    override fun getPlacesByUsername(username: String): List<PlaceDTO>? {
        return placeRepository.findPlacesByUsername(username)?.map { DataConverter.placeToDTO(it) }
    }
}