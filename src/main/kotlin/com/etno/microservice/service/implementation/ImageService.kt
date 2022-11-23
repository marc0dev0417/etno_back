package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.ImageDTO
import com.etno.microservice.repository.ImageRepository
import com.etno.microservice.service.ImageServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ImageService(
    private val imageRepository: ImageRepository
): ImageServiceInterface {
    override fun saveImage(imageDTO: ImageDTO): ImageDTO? {
        val itemImage = DataConverter.imageFromDTO(imageDTO)
        itemImage.idImage = UUID.randomUUID()
        val itemToSave = imageRepository.save(itemImage)

        return DataConverter.imageToDTO(itemToSave)
    }

    override fun getImages(): List<ImageDTO>? {
        return imageRepository.findAll().map { DataConverter.imageToDTO(it) }
    }
}