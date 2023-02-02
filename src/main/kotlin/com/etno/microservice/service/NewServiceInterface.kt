package com.etno.microservice.service

import com.etno.microservice.model.dto.NewDTO
import org.springframework.stereotype.Service

@Service
interface NewServiceInterface {
    fun getNews(): List<NewDTO>
    fun getNewsByUsername(username: String): List<NewDTO>?
    fun getNewByUsernameAndTitle(username: String, title: String): NewDTO?
}