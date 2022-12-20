package com.etno.microservice.service.implementation

import com.etno.microservice.model.Image
import com.etno.microservice.model.dto.PharmacyDTO
import com.etno.microservice.repository.ImageRepository
import com.etno.microservice.repository.PharmacyRepository
import com.etno.microservice.service.PharmacyServiceInterface
import com.etno.microservice.util.DataConverter
import com.etno.microservice.util.Urls
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.util.*

@Service
class PharmacyService(
    private val pharmacyRepository: PharmacyRepository,
    private val imageRepository: ImageRepository
): PharmacyServiceInterface{
    override fun getPharmacies(): List<PharmacyDTO> {
        return pharmacyRepository.findAll().map { DataConverter.pharmacyToDTO(it) }
    }

    override fun savePharmacy(pharmacyDTO: PharmacyDTO): PharmacyDTO? {
        val itemToSave = pharmacyRepository.save(DataConverter.pharmacyFromDTO(pharmacyDTO))
        return DataConverter.pharmacyToDTO(itemToSave)
    }

    override fun addImageToPharmacy(title: String, imageName: String): PharmacyDTO? {
        val pharmacyItem = pharmacyRepository.findPharmacyByName(title)
        val imageItem = imageRepository.findImageByName(imageName)

        pharmacyItem.imageUrl = imageItem.link
        val pharmacySaved = pharmacyRepository.save(pharmacyItem)

        return DataConverter.pharmacyToDTO(pharmacySaved)
    }
}