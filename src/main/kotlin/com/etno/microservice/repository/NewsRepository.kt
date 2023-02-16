package com.etno.microservice.repository

import com.etno.microservice.model.News
import com.etno.microservice.model.dto.pagination.NewsPageDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface NewsRepository: JpaRepository<News, UUID> {
    fun findNewByUsernameAndTitle(username: String, title: String): News?
    fun findNewsByUsername(username: String): List<News>?
    fun findNewsByUsernameAndCategory(username: String, category: String): List<News>?
    fun findNewsPageableByUsername(username: String, pageable: PageRequest): Page<News>?
}