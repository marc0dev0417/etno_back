package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.NewDTO
import com.etno.microservice.repository.NewRepository
import com.etno.microservice.service.NewServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service

@Service
class NewService(
    private val newRepository: NewRepository
): NewServiceInterface {
    override fun getNews(): List<NewDTO> {
        return newRepository.findAll().map { DataConverter.newToDTO(it) }
    }

    override fun getNewsByUsername(username: String): List<NewDTO>? {
        return newRepository.findNewsByUsername(username)?.map { DataConverter.newToDTO(it) }
    }

    override fun getNewByUsernameAndTitle(username: String, title: String): NewDTO? {
        return DataConverter.newToDTO(newRepository.findNewByUsernameAndTitle(username, title)!!)
    }

    override fun getNewByUsernameAndCategory(username: String, category: String): List<NewDTO>? {
        return newRepository.findNewsByUsernameAndCategory(username, category)?.map { DataConverter.newToDTO(it) }
    }
}