package com.etno.microservice.repository

import com.etno.microservice.model.SectionSubscribe
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SectionSubscribeRepository: JpaRepository<SectionSubscribe, UUID> {

}