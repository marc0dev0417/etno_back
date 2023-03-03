package com.etno.microservice.repository

import com.etno.microservice.model.Place
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlaceRepository: JpaRepository<Place, UUID> {

}