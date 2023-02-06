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
import java.util.*

@Service
class ImageService(
    private val imageRepository: ImageRepository
): ImageServiceInterface {
    override fun saveImage(
        multipartFile: MultipartFile,
        section: String,
        category: String,
        username: String
    ): ImageDTO? {
        val nameSectionPath: String = when(section){
            "evento" -> "events"
            "fiesta" -> "festivities"
            "turismo" -> "tourism"
            "noticia" -> "news"
            "farmacia" -> "pharmacies"
            "muerte" -> "deaths"
            "telefono" -> "phones"
            "incidente" -> "incidents"
            "patrocinador" -> "sponsors"
            else -> {"NO PATH :("}
        }
        //error(nameSectionPath)

        val routeBase = "${Urls.urlBase}images/$nameSectionPath/"
        val converterFile = File("${Urls.sourceImagePath}\\$nameSectionPath\\${multipartFile.originalFilename}")
        converterFile.createNewFile()

        val fos = FileOutputStream(converterFile)
        fos.write(multipartFile.bytes)
        fos.close()

        val itemImage = com.etno.microservice.model.Image()
        itemImage.idImage = UUID.randomUUID()
        itemImage.locality = username
        itemImage.section = section
        itemImage.name = multipartFile.originalFilename
        itemImage.category = category
        itemImage.link = "$routeBase${multipartFile.originalFilename}"

        val itemToSave = imageRepository.save(itemImage)

        return DataConverter.imageToDTO(itemToSave)
    }

    override fun getImages(): List<ImageDTO>? {
        return imageRepository.findAll().map { DataConverter.imageToDTO(it) }
    }

    override fun findImageByLocality(locality: String): List<ImageDTO>? {
        return imageRepository.findImagesByLocality(locality)?.map { DataConverter.imageToDTO(it) }
    }

    override fun deleteImage(name: String, section: String, locality: String): ImageDTO? {
        val imageItem = imageRepository.findImageByLocalityAndName(locality, name)

        val nameSectionPath: String = when(section.lowercase()){
            "evento" -> "events"
            "fiesta" -> "festivities"
            "turismo" -> "tourism"
            "noticia" -> "news"
            "muerte" -> "deaths"
            "farmacia" -> "pharmacies"
            "telefono" -> "phones"
            "patrocinador" -> "sponsors"
            else -> {"NO PATH :("}
        }

        val file = File("src\\main\\resources\\images\\$nameSectionPath\\${imageItem?.name}")

        if(imageItem?.name != null && file.exists()){
            imageRepository.delete(imageItem)
            file.delete()
        }
        return DataConverter.imageToDTO(imageItem!!)
    }
}