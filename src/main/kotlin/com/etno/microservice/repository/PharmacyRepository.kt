package com.etno.microservice.repository

import com.etno.microservice.model.Pharmacy
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PharmacyRepository: JpaRepository<Pharmacy, UUID> {
        fun findPharmacyByNameAndUsername(name: String, username: String): Pharmacy?
}