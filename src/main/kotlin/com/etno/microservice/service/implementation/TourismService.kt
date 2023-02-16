package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.TourismDTO
import com.etno.microservice.model.dto.pagination.NewsPageDTO
import com.etno.microservice.model.dto.pagination.TourismPageDTO
import com.etno.microservice.repository.ImageRepository
import com.etno.microservice.repository.TourismRepository
import com.etno.microservice.service.TourismServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.data.domain.PageRequest
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

    override fun getTourismByUsername(username: String): List<TourismDTO>? {
        return tourismRepository.findTourismByUsername(username)?.map { DataConverter.tourismToDTO(it) }
    }

    override fun getTourismPaginated(username: String, pageNum: Int, pageSize: Int): TourismPageDTO? {
        val pageable = PageRequest.of(pageNum, pageSize)
        val pagedResult = tourismRepository.findTourismPageableByUsername(username, pageable)
        return if (pagedResult!!.hasContent()){
            TourismPageDTO(
                content = pagedResult.content.toList().map { DataConverter.tourismToDTO(it) },
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        }else{
            TourismPageDTO(
                content = emptyList(),
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )

    }
    }
    /*
    override fun addImageToTourism(title: String, imageName: String): TourismDTO? {
        val tourismItem = tourismRepository.findTourismByTitle(title)
        val imageItem = imageRepository.findImageByName(imageName)

        tourismItem.images?.add(imageItem)
        val itemToSaved = tourismRepository.save(tourismItem)

        return DataConverter.tourismToDTO(itemToSaved)
    }
     */
}