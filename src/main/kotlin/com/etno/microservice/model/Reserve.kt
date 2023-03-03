package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "reserves")
data class Reserve(
    @Id
    @Type(type = "uuid-char")
    var idReserve: UUID ? = UUID.randomUUID(),

    @Column(name = "username")
    var username: String ? = null,

    @Column(name = "name")
    var name: String ? = null,

    @Column(name = "hall")
    var hall: String ? = null,

    @Column(name = "email")
    var email: String ? = null,

    @Column(name = "phone")
    var phone: String ? = null,

    @Column(name = "isPrivate")
    var isPrivate: Boolean ? = null,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], optional = true)
    @MapsId
    @JoinColumn(name = "idPlace", nullable = true)
    var place: Place ? = null,

    @Column(name = "date")
    var date: String ? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var reserveSchedules: MutableList<ReserveSchedule> ? = mutableListOf()
)