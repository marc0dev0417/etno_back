package com.etno.microservice.repository

import com.etno.microservice.model.Bando
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BandoRepository : JpaRepository<Bando, UUID> {

}