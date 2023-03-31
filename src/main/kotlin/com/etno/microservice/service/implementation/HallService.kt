package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.HallDTO
import com.etno.microservice.repository.HallRepository
import com.etno.microservice.service.HallServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service
import java.util.*

@Service
class HallService(
    private val hallRepository: HallRepository
): HallServiceInterface {
    override fun getHalls(): List<HallDTO>? {
        return hallRepository.findAll().map { DataConverter.hallToDTO(it) }
    }
    override fun saveHall(hallDTO: HallDTO): HallDTO? {
        val hallItem = DataConverter.hallFromDTO(hallDTO)
        hallItem.idHall = UUID.randomUUID()
        val itemToSave = hallRepository.save(hallItem)
        return DataConverter.hallToDTO(itemToSave)
    }
}