package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.SponsorDTO
import com.etno.microservice.repository.SponsorRepository
import com.etno.microservice.service.SponsorServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service

@Service
class SponsorService(
    private val sponsorRepository: SponsorRepository
): SponsorServiceInterface {
    override fun getSponsors(): List<SponsorDTO>? {
        return sponsorRepository.findAll().map { DataConverter.sponsorToDTO(it) }
    }

    override fun findSponsorsByUsername(username: String): List<SponsorDTO>? {
        return sponsorRepository.findSponsorsByUsername(username)?.map { DataConverter.sponsorToDTO(it) }
    }
}