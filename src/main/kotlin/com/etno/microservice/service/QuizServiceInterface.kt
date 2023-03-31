package com.etno.microservice.service

import com.etno.microservice.model.dto.QuizDTO
import org.springframework.stereotype.Service

@Service
interface QuizServiceInterface {
   fun getQuizzes(): List<QuizDTO>?
   fun deleteQuiz()
}