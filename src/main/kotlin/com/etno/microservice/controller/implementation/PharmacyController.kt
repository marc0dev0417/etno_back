package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.PharmacyControllerInterface
import com.etno.microservice.model.dto.PharmacyDTO
import com.etno.microservice.service.implementation.PharmacyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class PharmacyController(
    private val pharmacyService: PharmacyService
): PharmacyControllerInterface {
    override fun getPharmacies(): ResponseEntity<List<PharmacyDTO>> {
        return ResponseEntity.ok().body(pharmacyService.getPharmacies())
    }

    override fun savePharmacy(@RequestBody pharmacyDTO: PharmacyDTO): ResponseEntity<PharmacyDTO> {
        return ResponseEntity.ok().body(pharmacyService.savePharmacy(pharmacyDTO))
    }

    override fun addImageToPharmacy(title: String, imageName: String): ResponseEntity<PharmacyDTO> {
        return ResponseEntity.ok().body(pharmacyService.addImageToPharmacy(title, imageName))
    }
}