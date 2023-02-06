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
        val itemToSave = DataConverter.pharmacyFromDTO(pharmacyDTO)
        itemToSave.idPharmacy = UUID.randomUUID()

        val pharmacySaved = pharmacyRepository.save(DataConverter.pharmacyFromDTO(pharmacyDTO))
        return DataConverter.pharmacyToDTO(pharmacySaved)
    }

    override fun getPharmaciesByUsername(username: String): List<PharmacyDTO>? {
        return pharmacyRepository.findPharmacyByUsername(username)?.map { DataConverter.pharmacyToDTO(it) }
    }
}