package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "eventos")
data class Event(
    @Id
    @Type(type = "uuid-char")
    var idEvent: UUID = UUID.randomUUID(),

    @Column(name = "title")
    var title: String? = null
    )
