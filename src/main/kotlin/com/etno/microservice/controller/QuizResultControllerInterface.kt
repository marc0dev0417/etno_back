package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.QuizResultDTO
import com.etno.microservice.model.dto.pagination.QuizResultPageDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
interface QuizResultControllerInterface {
    @ApiOperation(
        value = "Get all quizResult",
        nickname = "getQuizResult",
        notes = "Will show all quizResult",
        tags = ["Quiz Result"],
        response = QuizResultDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Quiz Result", response = QuizResultDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/quiz_results"],
        produces = ["application/json"],
        params = ["username"],
        method = [RequestMethod.GET]
    )
    fun getQuizResults(
        @RequestParam(name = "username", required = true) username: String
    ): ResponseEntity<List<QuizResultDTO>>

    @ApiOperation(
        value = "Get quizResult by paginated",
        nickname = "getQuizResultPaginated",
        notes = "Will show quizzes paginated",
        tags = ["Bando"],
        response = QuizResultDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Quiz Result", response = QuizResultDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/quiz_results/paginated"],
        produces = ["application/json"],
        params = ["username", "pageNum", "elementSize"],
        method = [RequestMethod.GET]
    )
    fun findQuizResultPaginated(
        @RequestParam(name = "username") username: String,
        @RequestParam(defaultValue = "0", name = "pageNum") pageNum: Int,
        @RequestParam(defaultValue = "0", name = "elementSize") elementSize: Int
    ): ResponseEntity<QuizResultPageDTO>
}