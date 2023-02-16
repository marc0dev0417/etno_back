package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.DeathDTO
import com.etno.microservice.repository.DeathRepository
import com.etno.microservice.service.DeathServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service

@Service
class DeathService(
    private val deathRepository: DeathRepository
): DeathServiceInterface {
    override fun getDeaths(): List<DeathDTO>? {
        return deathRepository.findAll().map { DataConverter.deathToDTO(it) }
    }

    override fun getDeathsByUsername(username: String): List<DeathDTO>? {
        return deathRepository.findDeathsByUsername(username)?.map { DataConverter.deathToDTO(it) }
    }
}