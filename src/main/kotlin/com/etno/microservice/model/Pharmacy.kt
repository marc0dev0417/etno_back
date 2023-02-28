package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "pharmacies")
data class Pharmacy(
    @Id
    @Type(type = "uuid-char")
    var idPharmacy: UUID? = UUID.randomUUID(),

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

    @Column(name = "description", columnDefinition = "TEXT")
    var description: String ? = null,

    @Column(name = "latitude")
    var longitude: Double ? = null,

    @Column(name = "longitude")
    var latitude: Double ? = null,

    @Column(name = "startDate")
    var startDate: Date ? = null,

    @Column(name = "durationDays")
    var durationDays: Int ? = null,

    @Column(name = "frequencyInDays")
    var frequencyInDays: Int ? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var dates: MutableList<PharmacyDate>? = mutableListOf()
)