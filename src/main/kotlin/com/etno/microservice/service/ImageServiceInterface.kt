package com.etno.microservice.service

import com.etno.microservice.model.dto.ImageDTO
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
interface ImageServiceInterface {
    fun saveImage(multipartFile: MultipartFile, section: String): ImageDTO?
    fun getImages(): List<ImageDTO>?
    fun deleteImage(name: String, section: String): ImageDTO?
}