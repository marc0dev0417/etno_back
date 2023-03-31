package com.etno.microservice.repository

import com.etno.microservice.model.Quiz
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface QuizRepository: JpaRepository<Quiz, UUID> {

}