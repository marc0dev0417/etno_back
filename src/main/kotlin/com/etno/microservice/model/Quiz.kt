package com.etno.microservice.model

import com.google.api.client.util.DateTime
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "quizzes")
data class Quiz(
    @Id
    @Type(type = "uuid-char")
    var idQuiz: UUID ? = UUID.randomUUID(),

    @Column(name = "username")
    var username: String ? = null,

    @Column(name = "question")
    var question: String ? = null,

    @Column(name = "answerOne")
    var answerOne: String ? = null,

    @Column(name = "resultOne")
    var resultOne: Int ? = null,

    @Column(name = "answerTwo")
    var answerTwo: String ? = null,

    @Column(name = "resultTwo")
    var resultTwo: Int ? = null,

    @Column(name = "answerThree")
    var answerThree: String ? = null,

    @Column(name = "resultThree")
    var resultThree: Int ? = null,

    @Column(name = "answerFour")
    var answerFour: String ? = null,

    @Column(name = "resultFour")
    var resultFour: Int ? = null,

    @Column(name = "isActive")
    var isActive: Boolean ? = null,

    @Column(name = "datePicker")
    var datePicker: DateTime ? = null
)