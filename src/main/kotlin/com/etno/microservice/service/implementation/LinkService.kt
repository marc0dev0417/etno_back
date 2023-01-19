package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.LinkDTO
import com.etno.microservice.repository.LinkRepository
import com.etno.microservice.service.LinkServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service
import java.util.*

@Service
class LinkService(
    private val linkRepository: LinkRepository
): LinkServiceInterface {
    override fun getLinks(): List<LinkDTO>? {
        return linkRepository.findAll().map { DataConverter.linkToDTO(it) }
    }
    override fun saveLink(linkDTO: LinkDTO): LinkDTO? {
        val linkItem = DataConverter.linkFromDTO(linkDTO)
        linkItem.idLink = UUID.randomUUID()
        val itemSaved = linkRepository.save(linkItem)
        return DataConverter.linkToDTO(itemSaved)
    }
}