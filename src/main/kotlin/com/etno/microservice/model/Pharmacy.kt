package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "pharmacies")
data class Pharmacy(
    @Id
    @Type(type = "uuid-char")
    var idPharmacy: UUID = UUID.randomUUID(),

    @Column(name = "username")
    var username: String ? = null,

    @Column(name = "type")
    var type: String ? = null,

    @Column(name = "name")
    var name: String ? = null,

    @Column(name = "link")
    var link: String ? = null,

    @Column(name = "imageUrl")
    var imageUrl: String ? = null,

    @Column(name = "phone")
    var phone: String ? = null,

    @Column(name = "schedule")
    var schedule: String ? = null,

    @Column(name = "description")
    var description: String ? = null,

    @Column(name = "latitude")
    var longitude: String ? = null,

    @Column(name = "longitude")
    var latitude: String ? = null
)