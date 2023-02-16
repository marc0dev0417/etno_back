package com.etno.microservice.service

import com.etno.microservice.model.dto.SponsorDTO
import com.etno.microservice.model.dto.pagination.SponsorPageDTO
import org.springframework.stereotype.Service

@Service
interface SponsorServiceInterface {
    fun getSponsors(): List<SponsorDTO>?
    fun findSponsorsByUsername(username: String): List<SponsorDTO>?
    fun getSponsorPaginated(username: String, pageNum: Int, pageSize: Int): SponsorPageDTO?
}