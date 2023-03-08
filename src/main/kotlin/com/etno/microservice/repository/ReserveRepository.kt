package com.etno.microservice.repository

import com.etno.microservice.model.Reserve
import com.etno.microservice.model.dto.ReserveDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ReserveRepository: JpaRepository<Reserve, UUID> {
    fun findReservePageableByUsername(username: String, pageable: PageRequest): Page<Reserve>?
    fun findReserveByUsernameAndName(username: String, name: String): Reserve?
    fun getReservesByUsername(username: String): List<Reserve>?
    fun findReserveByUsernameAndIdReserve(username: String, idReserve: UUID): ReserveDTO?
}