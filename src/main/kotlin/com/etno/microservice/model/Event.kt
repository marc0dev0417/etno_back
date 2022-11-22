package com.etno.microservice.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "events")
data class Event(
    @Id
    var id: UUID = UUID.randomUUID(),

    @Column(name = "title")
    var title: String? = null
    )
