package com.etno.microservice.repository

import com.etno.microservice.model.New
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface NewRepository: JpaRepository<New, UUID> {
    fun findNewByUsernameAndTitle(username: String, title: String): New?
    fun findNewsByUsername(username: String): List<New>?
    fun findNewsByUsernameAndCategory(username: String, category: String): List<New>?
}