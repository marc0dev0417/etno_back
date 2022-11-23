package com.etno.microservice.service

import com.etno.microservice.model.dto.ImageDTO
import org.springframework.stereotype.Service

@Service
interface ImageServiceInterface {
    fun saveImage(imageDTO: ImageDTO): ImageDTO?
    fun getImages(): List<ImageDTO>?
}