package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "places")
data class Place(
    @Id
    @Type(type = "uuid-char")
    var idPlace: UUID ? = UUID.randomUUID(),

    @Column(name = "username")
    var username: String ? = null,

    @Column(name = "name")
    var name: String ? = null,

    @Column(name = "latitude")
    var latitude: Double ? = null,

    @Column(name = "longitude")
    var longitude: Double ? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var halls: MutableList<Hall> ? = mutableListOf()
)