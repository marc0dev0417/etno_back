package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.TourismDTO
import com.etno.microservice.repository.ImageRepository
import com.etno.microservice.repository.TourismRepository
import com.etno.microservice.service.TourismServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service
import java.util.*

@Service
class TourismService(
    private val tourismRepository: TourismRepository,
    private val imageRepository: ImageRepository
): TourismServiceInterface {
    override fun getTourism(): List<TourismDTO> {
        return tourismRepository.findAll().map { DataConverter.tourismToDTO(it) }
    }
    override fun saveTourism(tourismDTO: TourismDTO): TourismDTO? {
        val itemToSave = DataConverter.tourismFromDTO(tourismDTO)
        itemToSave.idTourism = UUID.randomUUID()
        val itemSaved = tourismRepository.save(itemToSave)

        return DataConverter.tourismToDTO(itemSaved)
    }
    override fun addImageToTourism(title: String, imageName: String): TourismDTO? {
        val tourismItem = tourismRepository.findTourismByTitle(title)
        val imageItem = imageRepository.findImageByName(imageName)

        tourismItem.images?.add(imageItem)
        val itemToSaved = tourismRepository.save(tourismItem)

        return DataConverter.tourismToDTO(itemToSaved)
    }
}