package com.etno.microservice.repository

import com.etno.microservice.model.Service
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ServiceRepository: JpaRepository<Service, UUID> {
    fun findServiceByUsernameAndOwner(username: String, owner: String): Service?
    fun findServiceByUsernameAndCategory(username: String, category: String): List<Service>?

    fun findServicePageableByUsername(username: String, pageable: PageRequest): Page<Service>?

}