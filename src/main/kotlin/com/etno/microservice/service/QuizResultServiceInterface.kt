package com.etno.microservice.service

import com.etno.microservice.model.dto.QuizResultDTO
import com.etno.microservice.model.dto.pagination.QuizResultPageDTO
import org.springframework.stereotype.Service

@Service
interface QuizResultServiceInterface {
    fun findQuizResultByUsername(username: String): List<QuizResultDTO>?
    fun getQuizResultPaginated(username: String, pageNum: Int, pageSize: Int): QuizResultPageDTO?
}