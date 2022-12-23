package com.etno.microservice.repository


import com.etno.microservice.model.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EventRepository: JpaRepository<Event, UUID> {
    fun findEventByTitleAndUsername(title: String, username: String): Event?
}