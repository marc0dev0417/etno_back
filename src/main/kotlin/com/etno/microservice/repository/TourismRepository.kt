package com.etno.microservice.repository

import com.etno.microservice.model.Tourism
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TourismRepository: JpaRepository<Tourism, UUID> {
    fun findTourismByTitleAndUsername(title: String, username: String): Tourism?
    fun findTourismByUsername(username: String): List<Tourism>?
    fun findTourismPageableByUsername(username: String, pageable: PageRequest): Page<Tourism>?
}