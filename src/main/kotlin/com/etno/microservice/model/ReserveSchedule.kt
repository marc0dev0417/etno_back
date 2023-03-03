package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "reserveSchedules")
data class ReserveSchedule(
    @Id
    @Type(type = "uuid-char")
    var idReserveSchedule: UUID ? = UUID.randomUUID(),

    @Column(name = "date")
    var date: String ? = null
)
