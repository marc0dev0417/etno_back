package com.etno.microservice.service.implementation

import com.etno.microservice.exception.Constants
import com.etno.microservice.exception.handler.ListEmptyException
import com.etno.microservice.model.dto.FestivityDTO
import com.etno.microservice.repository.FestivityRepository
import com.etno.microservice.repository.ImageRepository
import com.etno.microservice.service.FestivityServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service
import java.util.*

@Service
class FestivityService(
    private val festivityRepository: FestivityRepository,
    private val imageRepository: ImageRepository
): FestivityServiceInterface {
    override fun getFestivities(): List<FestivityDTO>? {
        if(festivityRepository.findAll().isEmpty()){
            throw ListEmptyException(Constants.LIST_EMPTY.code, Constants.LIST_EMPTY)
        }
        return festivityRepository.findAll().map { DataConverter.festivityToDTO(it) }
    }

    override fun saveFestivity(festivityDTO: FestivityDTO): FestivityDTO? {
        val itemToSave = DataConverter.festivityFromDTO(festivityDTO)
        itemToSave.idFestivity = UUID.randomUUID()
        val itemSaved = festivityRepository.save(itemToSave)
        return DataConverter.festivityToDTO(itemSaved)
    }

    override fun deleteFestivityByTitle(title: String): FestivityDTO? {
        val itemToDelete = festivityRepository.findFestivityByTitle(title)
        festivityRepository.delete(itemToDelete)
        return DataConverter.festivityToDTO(itemToDelete)
    }

    override fun addImageToFestivity(title: String, name: String): FestivityDTO? {
        val festivityToSave = festivityRepository.findFestivityByTitle(title)
        val imageToSave = imageRepository.findImageByName(name)

        festivityToSave.images?.add(imageToSave!!)
        val festivitySaved = festivityRepository.save(festivityToSave)

        return DataConverter.festivityToDTO(festivitySaved)
    }

    override fun deleteImageFromFestivity(title: String, name: String): FestivityDTO? {
        val festivityItem = festivityRepository.findFestivityByTitle(title)
        val imageToDelete = imageRepository.findImageByName(name)

        festivityItem.images?.remove(imageToDelete)

        return DataConverter.festivityToDTO(festivityItem)
    }
}