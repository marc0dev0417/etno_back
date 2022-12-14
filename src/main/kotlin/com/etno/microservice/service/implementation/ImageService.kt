package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.ImageDTO
import com.etno.microservice.repository.ImageRepository
import com.etno.microservice.service.ImageServiceInterface
import com.etno.microservice.util.DataConverter
import com.etno.microservice.util.Urls
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

@Service
class ImageService(
    private val imageRepository: ImageRepository
): ImageServiceInterface {
    override fun saveImage(multipartFile: MultipartFile, section: String): ImageDTO? {
        val nameSectionPath: String = when(section){
            "event" -> "events"
            "festivity" -> "festivities"
            "tourism" -> "tourism"
            "new" -> "news"

            "evento" -> "events"
            "fiestividad" -> "festivities"
            "tourismo" -> "tourism"
            "noticia" -> "news"
            else -> {"NO PATH :("}
        }

        val routeBase = "http://192.168.137.1:8080/images/$nameSectionPath/"
        val converterFile = File("${Urls.sourceImagePath}\\$nameSectionPath\\${multipartFile.originalFilename}")
        converterFile.createNewFile()

        val fos = FileOutputStream(converterFile)
        fos.write(multipartFile.bytes)
        fos.close()

        val itemImage = com.etno.microservice.model.Image()
        itemImage.idImage = UUID.randomUUID()
        itemImage.name = multipartFile.originalFilename
        itemImage.link = "$routeBase${multipartFile.originalFilename}"

        val itemToSave = imageRepository.save(itemImage)

        return DataConverter.imageToDTO(itemToSave)
    }

    override fun getImages(): List<ImageDTO>? {
        return imageRepository.findAll().map { DataConverter.imageToDTO(it) }
    }

    override fun deleteImage(name: String, section: String): ImageDTO? {
        val imageItem = imageRepository.findImageByName(name)

        val nameSectionPath: String = when(section){
            "event" -> "events"
            "festivity" -> "festivities"
            "tourism" -> "tourism"
            "new" -> "news"

            "evento" -> "events"
            "festividad" -> "festivities"
            "tourismo" -> "tourism"
            "noticia" -> "news"
            else -> {"NO PATH :("}
        }

        val file = File("src\\main\\resources\\images\\$nameSectionPath\\${imageItem.name}")

        if(imageItem.name != null && file.exists()){
            imageRepository.delete(imageItem)
            file.delete()
        }
        return DataConverter.imageToDTO(imageItem)
    }
}