package com.etno.microservice.repository

import com.etno.microservice.model.QuizResult
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface QuizResultRepository: JpaRepository<QuizResult, UUID> {
   fun findQuizResultByUsername(username: String): List<QuizResult>?
   fun findQuizResultPageableByUsername(username: String, pageable: PageRequest): Page<QuizResult>?
}