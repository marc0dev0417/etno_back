package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.ImageDTO
import com.etno.microservice.model.dto.pagination.ImagePageDTO
import com.etno.microservice.repository.ImageRepository
import com.etno.microservice.service.ImageServiceInterface
import com.etno.microservice.util.DataConverter
import com.etno.microservice.util.Urls
import org.springframework.data.domain.PageRequest
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
            "bando" -> "bandos"
            "enseres" -> "enseres"
            "anuncio" -> "advertisements"
            "servicio" -> "services"
            "fiesta" -> "festivities"
            "lugar" -> "place"
            "turismo" -> "tourism"
            "noticia" -> "news"
            "farmacia" -> "pharmacies"
            "muerte" -> "deaths"
            "servicio" -> "services"
            "incidente" -> "incidents"
            "patrocinador" -> "sponsors"
            "link" -> "links_custom"
            else -> {"NO PATH :("}
        }
        //error(nameSectionPath)

        val routeBase = "${Urls.urlBase}images/$nameSectionPath/"
        val converterFile = File("${Urls.sourceImagePath}\\$nameSectionPath\\${multipartFile.originalFilename}")
        /*
        var bufferedImage = ImageIO.read(converterFile)
        val image = bufferedImage.getScaledInstance(1920, 1080, Image.SCALE_DEFAULT)
        bufferedImage = BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB)
        bufferedImage.graphics.drawImage(image,0,0,null)
         */
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
            "enseres" -> "enseres"
            "turismo" -> "tourism"
            "anuncio" -> "advertisements"
            "noticia" -> "news"
            "muerte" -> "deaths"
            "farmacia" -> "pharmacies"
            "telefono" -> "phones"
            "patrocinador" -> "sponsors"
            "services" -> "services"
            "link" -> "links_custom"
            else -> {"NO PATH :("}
        }

        val file = File("src\\main\\resources\\images\\$nameSectionPath\\${imageItem?.name}")

        if(imageItem?.name != null && file.exists()){
            imageRepository.delete(imageItem)
            file.delete()
        }
        return DataConverter.imageToDTO(imageItem!!)
    }

    override fun getImagesPaginated(locality: String, pageNum: Int, pageSize: Int): ImagePageDTO? {
        val pageable = PageRequest.of(pageNum, pageSize)
        val pagedResult = imageRepository.findEventsPageableByLocality(locality, pageable)
        return if (pagedResult!!.hasContent()){
            ImagePageDTO(
                content = pagedResult.content.toList().map { DataConverter.imageToDTO(it) },
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        } else {
            ImagePageDTO(
                content = emptyList(),
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        }
    }
}