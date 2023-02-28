package com.etno.microservice.service

import com.etno.microservice.model.dto.ImageDTO
import com.etno.microservice.model.dto.pagination.ImagePageDTO
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
interface ImageServiceInterface {
    fun saveImage(multipartFile: MultipartFile, section: String, category: String, username: String): ImageDTO?
    fun getImages(): List<ImageDTO>?
    fun findImageByLocality(locality: String): List<ImageDTO>?
    fun deleteImage(name: String, section: String, locality: String): ImageDTO?
    fun getImagesPaginated(locality: String, pageNum: Int, pageSize: Int): ImagePageDTO?
}