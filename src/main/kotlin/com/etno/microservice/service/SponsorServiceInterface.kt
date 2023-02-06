package com.etno.microservice.service

import com.etno.microservice.model.dto.SponsorDTO
import org.springframework.stereotype.Service

@Service
interface SponsorServiceInterface {
    fun getSponsors(): List<SponsorDTO>?
    fun findSponsorsByUsername(username: String): List<SponsorDTO>?
}