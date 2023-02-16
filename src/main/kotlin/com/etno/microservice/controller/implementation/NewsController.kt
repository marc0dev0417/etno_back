package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.NewsControllerInterface
import com.etno.microservice.model.dto.NewsDTO
import com.etno.microservice.model.dto.pagination.NewsPageDTO
import com.etno.microservice.service.implementation.NewsService
import io.swagger.models.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class NewsController(
    private val newService: NewsService
): NewsControllerInterface{
    override fun findNewsPaginated(username: String, pageNum: Int, elementSize: Int): ResponseEntity<NewsPageDTO> {
        return ResponseEntity.ok().body(newService.getNewsPaginated(username, pageNum, elementSize))
    }

    override fun getNews(): ResponseEntity<List<NewsDTO>> {
        return ResponseEntity.ok().body(newService.getNews())
    }

    override fun getNewsByUsername(username: String): ResponseEntity<List<NewsDTO>> {
        return ResponseEntity.ok().body(newService.getNewsByUsername(username))
    }

    override fun findNewsByUsernameAndCategory(username: String, category: String): ResponseEntity<List<NewsDTO>> {
        return ResponseEntity.ok().body(newService.getNewByUsernameAndCategory(username, category))
    }


}