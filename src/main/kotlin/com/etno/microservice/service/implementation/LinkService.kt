package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.LinkDTO
import com.etno.microservice.model.dto.pagination.LinkPageDTO
import com.etno.microservice.repository.LinkRepository
import com.etno.microservice.service.LinkServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.data.domain.PageRequest
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

    override fun findLinksByUsername(username: String): List<LinkDTO>? {
        return linkRepository.findLinksByUsername(username)?.map { DataConverter.linkToDTO(it) }
    }

    override fun getLinkPaginated(username: String, pageNum: Int, pageSize: Int): LinkPageDTO? {
        val pageable = PageRequest.of(pageNum, pageSize)
        val pagedResult = linkRepository.findLinkPageableByUsername(username, pageable)
        return if (pagedResult!!.hasContent()){
            LinkPageDTO(
                content = pagedResult.content.toList().map { DataConverter.linkToDTO(it) },
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        }else{
            LinkPageDTO(
                content = emptyList(),
                totalElements = pagedResult.totalElements,
                totalPages = pagedResult.totalPages,
                pageNum = pagedResult.number
            )
        }
    }
}