package com.etno.microservice.repository

import com.etno.microservice.model.Bando
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BandoRepository : JpaRepository<Bando, UUID> {
    fun findBandoByUsernameAndTitle(username: String, title: String): Bando?
    fun findBandosByUsername(username: String): List<Bando>?
    fun findBandoPageableByUsername(username: String, pageable: PageRequest): Page<Bando>?
}