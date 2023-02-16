package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.SponsorDTO
import com.etno.microservice.model.dto.pagination.SponsorPageDTO
import com.etno.microservice.repository.SponsorRepository
import com.etno.microservice.service.SponsorServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.data.domain.PageRequest
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

    override fun getSponsorPaginated(username: String, pageNum: Int, pageSize: Int): SponsorPageDTO? {
        val pageable = PageRequest.of(pageNum, pageSize)
        val pagedResult = sponsorRepository.findSponsorPageableByUsername(username, pageable)
        return if (pagedResult!!.hasContent()){
            SponsorPageDTO(
                content = pagedResult.content.toList().map { DataConverter.sponsorToDTO(it) },
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        } else {
            SponsorPageDTO(
                content = emptyList(),
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        }
    }
}