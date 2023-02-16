package com.etno.microservice.service

import com.etno.microservice.model.dto.TourismDTO
import com.etno.microservice.model.dto.pagination.TourismPageDTO
import org.springframework.stereotype.Service

@Service
interface TourismServiceInterface {
    fun getTourism(): List<TourismDTO>?
    fun saveTourism(tourismDTO: TourismDTO): TourismDTO?
    //fun addImageToTourism(title: String, imageName: String): TourismDTO?
    fun getTourismByUsername(username: String): List<TourismDTO>?

    fun getTourismPaginated(username: String, pageNum: Int, pageSize : Int): TourismPageDTO?
}