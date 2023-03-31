package com.etno.microservice.service

import com.etno.microservice.model.dto.CustomLinkDTO
import com.etno.microservice.model.dto.pagination.CustomLinkPageDTO
import org.springframework.stereotype.Service

@Service
interface CustomLinkServiceInterface {
     fun getCustomLinks(): List<CustomLinkDTO>?
     fun getCustomLinksByLocality(username: String): List<CustomLinkDTO>?
     fun getCustomLinkPaginated(username: String, pageNum: Int, pageSize : Int): CustomLinkPageDTO?
}