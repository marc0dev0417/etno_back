package com.etno.microservice.repository

import com.etno.microservice.model.Phone
import com.etno.microservice.model.dto.PhoneDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.awt.print.Pageable
import java.util.UUID

@Repository
interface PhoneRepository: JpaRepository<Phone, UUID> {
    fun findPhoneByUsernameAndOwner(username: String, owner: String): Phone?
    fun findPhoneByUsernameAndCategory(username: String, category: String): List<Phone>?

}