package com.etno.microservice.service

import com.etno.microservice.model.dto.PlaceDTO
import com.etno.microservice.model.dto.pagination.PlacePageDTO
import org.springframework.stereotype.Service

@Service
interface PlaceServiceInterface {
    fun getPlaces(): List<PlaceDTO> ?
    fun savePlace(placeDTO: PlaceDTO): PlaceDTO?
    fun getPlacesByUsername(username: String): List<PlaceDTO>?
    fun getPlacesPaginated(username: String, pageNum: Int, pageSize: Int): PlacePageDTO?
}