package com.etno.microservice.service

import com.etno.microservice.model.dto.NewsDTO
import com.etno.microservice.model.dto.pagination.NewsPageDTO
import org.springframework.stereotype.Service

@Service
interface NewsServiceInterface {
    fun getNews(): List<NewsDTO>
    fun getNewsByUsername(username: String): List<NewsDTO>?
    fun getNewByUsernameAndTitle(username: String, title: String): NewsDTO?
    fun getNewByUsernameAndCategory(username: String, category: String): List<NewsDTO>?
    fun getNewsPaginated(username: String, pageNum: Int, pageSize : Int): NewsPageDTO?
}