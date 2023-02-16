package com.etno.microservice.repository

import com.etno.microservice.model.Ad
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.awt.print.Pageable
import java.util.UUID

@Repository
interface AdRepository: JpaRepository<Ad, UUID> {
    fun findAdByUsernameAndTitle(username: String, title: String): Ad?
    fun findAdsByUsername(username: String): List<Ad>?
    fun findAdsPageableByUsername(username: String, pageable: PageRequest): Page<Ad>?
}