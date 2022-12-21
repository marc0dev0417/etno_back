package com.etno.microservice.service

import com.etno.microservice.model.dto.TourismDTO
import org.springframework.stereotype.Service

@Service
interface TourismServiceInterface {
    fun getTourism(): List<TourismDTO>?
    fun saveTourism(tourismDTO: TourismDTO): TourismDTO?
    fun addImageToTourism(title: String, imageName: String): TourismDTO?
}