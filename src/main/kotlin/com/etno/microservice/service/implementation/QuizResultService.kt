package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.QuizResultDTO
import com.etno.microservice.model.dto.pagination.QuizResultPageDTO
import com.etno.microservice.repository.QuizResultRepository
import com.etno.microservice.service.QuizResultServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class QuizResultService(
    private val quizResultRepository: QuizResultRepository
): QuizResultServiceInterface {
    override fun findQuizResultByUsername(username: String): List<QuizResultDTO>? {
        return quizResultRepository.findQuizResultByUsername(username)?.map { DataConverter.quizResultToDTO(it) }
    }

    override fun getQuizResultPaginated(username: String, pageNum: Int, pageSize: Int): QuizResultPageDTO? {
        val pageable = PageRequest.of(pageNum, pageSize)
        val pagedResult = quizResultRepository.findQuizResultPageableByUsername(username, pageable)

        return if (pagedResult!!.hasContent()){
            QuizResultPageDTO(
                content = pagedResult.content.toList().map { DataConverter.quizResultToDTO(it) },
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        } else {
            QuizResultPageDTO(
                content = emptyList(),
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        }
    }
}