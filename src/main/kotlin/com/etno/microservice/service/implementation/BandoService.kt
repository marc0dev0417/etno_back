package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.BandoDTO
import com.etno.microservice.repository.BandoRepository
import com.etno.microservice.service.BandoServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service
import java.util.*

@Service
class BandoService(
    private val bandoRepository: BandoRepository
): BandoServiceInterface {
    override fun getBando(): List<BandoDTO> {
       return bandoRepository.findAll().map { DataConverter.bandoToDTO(it) }
    }
    override fun saveBando(bandoDTO: BandoDTO): BandoDTO {
        val bandoItem = DataConverter.bandoFromDTO(bandoDTO)
        bandoItem.idBando = UUID.randomUUID()
        val bandoItemToSaved = bandoRepository.save(bandoItem)
        return DataConverter.bandoToDTO(bandoItemToSaved)
    }
}