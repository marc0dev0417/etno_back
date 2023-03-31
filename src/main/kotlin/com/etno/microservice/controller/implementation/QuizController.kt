package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.QuizControllerInterface
import com.etno.microservice.model.dto.QuizDTO
import com.etno.microservice.service.implementation.QuizService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class QuizController(
    private val quizService: QuizService
): QuizControllerInterface{
    override fun getQuizzes(): ResponseEntity<List<QuizDTO>> {
        return ResponseEntity.ok().body(quizService.getQuizzes())
    }
}