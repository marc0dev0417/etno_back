package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.NewsDTO
import com.etno.microservice.model.dto.pagination.EventPageDTO
import com.etno.microservice.model.dto.pagination.NewsPageDTO
import com.etno.microservice.repository.NewsRepository
import com.etno.microservice.service.NewsServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class NewsService(
    private val newsRepository: NewsRepository
): NewsServiceInterface {
    override fun getNews(): List<NewsDTO> {
        return newsRepository.findAll().map { DataConverter.newsToDTO(it) }
    }

    override fun getNewsByUsername(username: String): List<NewsDTO>? {
        return newsRepository.findNewsByUsername(username)?.map { DataConverter.newsToDTO(it) }
    }

    override fun getNewByUsernameAndTitle(username: String, title: String): NewsDTO? {
        return DataConverter.newsToDTO(newsRepository.findNewByUsernameAndTitle(username, title)!!)
    }

    override fun getNewByUsernameAndCategory(username: String, category: String): List<NewsDTO>? {
        return newsRepository.findNewsByUsernameAndCategory(username, category)?.map { DataConverter.newsToDTO(it) }
    }

    override fun getNewsPaginated(username: String, pageNum: Int, pageSize: Int): NewsPageDTO? {
        val pageable = PageRequest.of(pageNum, pageSize)
        val pagedResult = newsRepository.findNewsPageableByUsername(username, pageable)
        return if (pagedResult!!.hasContent()){
            NewsPageDTO(
                    content = pagedResult.content.toList().map { DataConverter.newsToDTO(it)!! },
                    totalElements = pagedResult.totalElements,
                    totalPages = pagedResult.totalPages,
                    pageNum = pagedResult.number
            )
        }else{
            NewsPageDTO(
                    content = emptyList(),
                    totalElements = pagedResult.totalElements,
                    totalPages = pagedResult.totalPages,
                    pageNum = pagedResult.number
            )
        }
    }
}