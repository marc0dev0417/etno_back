package com.etno.microservice.repository


import com.etno.microservice.model.Event
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.awt.print.Pageable
import java.util.*

@Repository
interface EventRepository: JpaRepository<Event, UUID> {
    fun findEventByTitleAndUsername(title: String, username: String): Event?
    fun findEventsByUsername(username: String): List<Event>?
    fun findEventsPageableByUsername(username: String, pageable: PageRequest): Page<Event>?
}