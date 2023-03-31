package com.etno.microservice.repository

import com.etno.microservice.model.CustomLink
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomLinkRepository: JpaRepository<CustomLink, UUID> {
    fun findCustomLinkByUsernameAndIdCustomLink(username: String, idCustomLink: UUID): CustomLink?
    fun findCustomLinkByUsername(username: String): List<CustomLink>?
    fun findCustomLinkPageableByUsername(username: String, pageable: PageRequest): Page<CustomLink>?
}