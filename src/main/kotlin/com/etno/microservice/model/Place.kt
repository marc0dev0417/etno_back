package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "places")
data class Place(
    @Id
    @Type(type = "uuid-char")
    var idPlace: UUID ? = UUID.randomUUID(),

    @Column(name = "username")
    var username: String ? = null,

    @Column(name = "placeName")
    var placeName: String ? = null,

    @Column(name = "latitude")
    var latitude: Double ? = null,

    @Column(name = "longitude")
    var longitude: Double ? = null,
)