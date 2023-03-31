package com.etno.microservice.repository

import com.etno.microservice.model.Place
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlaceRepository: JpaRepository<Place, UUID> {
    fun findPlaceByUsernameAndName(username: String, name: String): Place?
    fun findPlacesByUsername(username: String): List<Place>?
    fun findPlaceByUsernameAndIdPlace(username: String, idPlace: UUID): Place?
    fun findPlacePageableByUsername(username: String, pageable: PageRequest): Page<Place>?
}