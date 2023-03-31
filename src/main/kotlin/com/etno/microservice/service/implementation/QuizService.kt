package com.etno.microservice.service.implementation

import com.etno.microservice.model.QuizResult
import com.etno.microservice.model.dto.QuizDTO
import com.etno.microservice.repository.QuizRepository
import com.etno.microservice.repository.QuizResultRepository
import com.etno.microservice.repository.UserRepository
import com.etno.microservice.service.QuizServiceInterface
import com.etno.microservice.util.DataConverter
import com.google.api.client.util.DateTime
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class QuizService(
    private val quizRepository: QuizRepository,
    private val userRepository: UserRepository,
    private val quizResultRepository: QuizResultRepository
): QuizServiceInterface {
    override fun getQuizzes(): List<QuizDTO> {
        return quizRepository.findAll().map { DataConverter.quizToDTO(it) }
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    override fun deleteQuiz() {
        quizRepository.findAll().forEach { quiz ->
            if (quiz.datePicker?.value!! <= DateTime(Date()).value) {
                userRepository.findAll().forEach {
                    val userQuiz = it.quizzes?.find { it.idQuiz == quiz.idQuiz }
                    quizResultRepository.save(
                        QuizResult(
                            idQuizResult = UUID.randomUUID(),
                            username = userQuiz?.username,
                            question = userQuiz?.question,
                            answerOne = userQuiz?.answerOne,
                            resultOne = userQuiz?.resultOne,
                            answerTwo = userQuiz?.answerTwo,
                            resultTwo = userQuiz?.resultTwo,
                            answerThree = userQuiz?.answerThree,
                            resultThree = userQuiz?.resultThree,
                            answerFour = userQuiz?.answerFour,
                            resultFour = userQuiz?.resultFour
                        ))
                    it.quizzes?.remove(userQuiz!!)
                }
                 quizRepository.delete(quiz)
            }
        }
    }
}