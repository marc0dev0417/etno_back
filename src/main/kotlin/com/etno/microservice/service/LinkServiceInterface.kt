package com.etno.microservice.service

import com.etno.microservice.model.dto.LinkDTO
import org.springframework.stereotype.Service

@Service
interface LinkServiceInterface {
    fun getLinks(): List<LinkDTO>?
    fun saveLink(linkDTO: LinkDTO): LinkDTO?
    fun findLinksByUsername(username: String): List<LinkDTO>?
}