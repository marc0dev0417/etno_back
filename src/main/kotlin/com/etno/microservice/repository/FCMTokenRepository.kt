package com.etno.microservice.repository

import com.etno.microservice.model.FCMToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface FCMTokenRepository: JpaRepository<FCMToken, UUID> {

}