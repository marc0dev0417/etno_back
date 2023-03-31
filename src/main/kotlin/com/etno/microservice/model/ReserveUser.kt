package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "reserveUsers")
data class ReserveUser(
    @Id
    @Type(type = "uuid-char")
    var idReserveUser: UUID ? = UUID.randomUUID(),

    @Column(name = "fcmToken")
    var fcmToken: String ? = null,

    @Column(name = "data")
    var data: String ? = null,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REFRESH], optional = true)
    var place: Place ? = null,

    @Column(name = "isReserved")
    var isReserved: Boolean ? = null,

    @Column(name = "description")
    var description: String ? = null,

    @Column(name = "reservePhone")
    var reservePhone: String ? = null,

    @Column(name = "latitude")
    var latitude: Double ? = null,

    @Column(name = "longitude")
    var longitude: Double ? = null,

    @Column(name = "date")
    var date: String ? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var reserveSchedules: MutableList<ReserveSchedule> ? = mutableListOf(),
)