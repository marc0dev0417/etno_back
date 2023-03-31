package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.api.client.util.DateTime
import java.util.*

data class QuizDTO(
    @JsonProperty("idQuiz") var idQuiz: UUID ? = UUID.randomUUID(),

    @JsonProperty("username") var username: String ? = null,

    @JsonProperty("question") var question: String ? = null,

    @JsonProperty("answerOne") var answerOne: String ? = null,
    @JsonProperty("resultOne") var resultOne: Int ? = null,

    @JsonProperty("answerTwo") var answerTwo: String ? = null,
    @JsonProperty("resultTwo") var resultTwo: Int ? = null,

    @JsonProperty("answerThree") var answerThree: String ? = null,
    @JsonProperty("resultThree") var resultThree: Int ? = null,

    @JsonProperty("answerFour") var answerFour: String ? = null,
    @JsonProperty("resultFour") var resultFour: Int ? = null,

    @JsonProperty("isActive") var isActive: Boolean ? = false,
    @JsonProperty("datePicker") var datePicker: DateTime ? = null
)