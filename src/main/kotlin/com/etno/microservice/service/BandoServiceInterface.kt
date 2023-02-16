package com.etno.microservice.service

import com.etno.microservice.model.dto.BandoDTO
import com.etno.microservice.model.dto.pagination.BandoPageDTO
import org.springframework.stereotype.Service

@Service
interface BandoServiceInterface {
    fun getBando() : List<BandoDTO>?
    fun saveBando(bandoDTO: BandoDTO): BandoDTO
    fun getBandosByUsername(username: String): List<BandoDTO>
    fun getBandoByUsernameAndTitle(username: String, title: String): BandoDTO?
    fun getBandoPaginated(username: String, pageNum: Int, pageSize: Int): BandoPageDTO?
}