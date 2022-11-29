package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.ImageControllerInterface
import com.etno.microservice.model.dto.ImageDTO
import com.etno.microservice.service.implementation.ImageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class ImageController(
    private val imageService: ImageService
): ImageControllerInterface {
    override fun saveImage(multipartFile: MultipartFile): ResponseEntity<ImageDTO>? {
        return ResponseEntity.ok().body(imageService.saveImage(multipartFile))
    }

    override fun getImages(): ResponseEntity<List<ImageDTO>>? {
        return ResponseEntity.ok().body(imageService.getImages())
    }

    override fun deleteImage(name: String): ResponseEntity<ImageDTO>? {
        return ResponseEntity.ok().body(imageService.deleteImage(name))
    }
}