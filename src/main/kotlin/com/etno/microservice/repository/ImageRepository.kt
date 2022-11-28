package com.etno.microservice.repository

import com.etno.microservice.model.Image
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ImageRepository: JpaRepository<Image, UUID> {
    fun findImageByName(name: String): Image
}