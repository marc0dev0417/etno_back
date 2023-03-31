package com.etno.microservice.repository

import com.etno.microservice.model.Hall
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HallRepository: JpaRepository<Hall, UUID> {
    fun findHallByUsernameAndName(username: String, name: String): Hall?
    fun findHallByUsernameAndIdHall(username: String, idHall: UUID): Hall?
}