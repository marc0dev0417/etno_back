package com.etno.microservice.repository

import com.etno.microservice.model.Sponsor
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface SponsorRepository: JpaRepository<Sponsor, UUID> {
    fun findSponsorByUsernameAndTitle(username: String, title: String): Sponsor?
    fun findSponsorsByUsername(username: String): List<Sponsor>?
    fun findSponsorPageableByUsername(username: String, pageable: PageRequest): Page<Sponsor>?
}