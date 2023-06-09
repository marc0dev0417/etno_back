package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.ImageControllerInterface
import com.etno.microservice.model.dto.ImageDTO
import com.etno.microservice.model.dto.pagination.ImagePageDTO
import com.etno.microservice.service.implementation.ImageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class ImageController(
    private val imageService: ImageService
): ImageControllerInterface {
    override fun saveImage(
        multipartFile: MultipartFile,
        section: String,
        category: String,
        username: String
    ): ResponseEntity<ImageDTO>? {
        return ResponseEntity.ok().body(imageService.saveImage(multipartFile, section, category, username))
    }

    override fun getImages(): ResponseEntity<List<ImageDTO>>? {
        return ResponseEntity.ok().body(imageService.getImages())
    }

    override fun deleteImage(name: String, section: String, locality: String): ResponseEntity<ImageDTO>? {
        return ResponseEntity.ok().body(imageService.deleteImage(name, section, locality))
    }

    override fun findImagesByLocality(locality: String): ResponseEntity<List<ImageDTO>> {
        return ResponseEntity.ok().body(imageService.findImageByLocality(locality))
    }

    override fun findImagesPaginated(locality: String, pageNum: Int, elementSize: Int): ResponseEntity<ImagePageDTO> {
        return ResponseEntity.ok().body(imageService.getImagesPaginated(locality, pageNum, elementSize))
    }
}