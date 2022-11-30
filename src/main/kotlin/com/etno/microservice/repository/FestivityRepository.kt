package com.etno.microservice.repository

import com.etno.microservice.model.Festivity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface FestivityRepository: JpaRepository<Festivity, UUID> {
    fun findFestivityByTitle(title: String): Festivity
}