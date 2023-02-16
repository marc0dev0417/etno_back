package com.etno.microservice.repository

import com.etno.microservice.model.Death
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface DeathRepository: JpaRepository<Death, UUID> {
    fun findDeathByUsernameAndName(username: String, name: String): Death?
    fun findDeathsByUsername(username: String): List<Death>?
    fun findDeathPageableByUsername(username: String, pageable: PageRequest): Page<Death>?
}