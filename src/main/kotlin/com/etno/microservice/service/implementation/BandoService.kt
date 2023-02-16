package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.BandoDTO
import com.etno.microservice.model.dto.pagination.BandoPageDTO
import com.etno.microservice.repository.BandoRepository
import com.etno.microservice.service.BandoServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.data.domain.PageRequest
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

    override fun getBandosByUsername(username: String): List<BandoDTO> {
        return bandoRepository.findBandosByUsername(username)!!.map { DataConverter.bandoToDTO(it) }
    }

    override fun getBandoByUsernameAndTitle(username: String, title: String): BandoDTO? {
        return DataConverter.bandoToDTO(bandoRepository.findBandoByUsernameAndTitle(username, title)!!)
    }

    override fun getBandoPaginated(username: String, pageNum: Int, pageSize: Int): BandoPageDTO? {
        val pageable = PageRequest.of(pageNum, pageSize)
        val pagedResult = bandoRepository.findBandoPageableByUsername(username, pageable)

        return if (pagedResult!!.hasContent()){
            BandoPageDTO(
                    content = pagedResult.content.toList().map { DataConverter.bandoToDTO(it) },
                    totalElements = pagedResult.totalElements,
                    totalPages = pagedResult.totalPages,
                    pageNum = pagedResult.number
            )
        } else {
            BandoPageDTO(
                    content = emptyList(),
                    totalElements = pagedResult.totalElements,
                    totalPages = pagedResult.totalPages,
                    pageNum = pagedResult.number
            )
        }
    }
}