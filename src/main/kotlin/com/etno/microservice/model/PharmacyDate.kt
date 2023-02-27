package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "pharmacyDates")
data class PharmacyDate(
    @Id
    @Type(type = "uuid-char")
    var idPharmacyDate: UUID ? = UUID.randomUUID(),

    @Column(name = "username")
    var username: String ? = null,

    @Column(name = "namePharmacy")
    var namePharmacy: String ? = null,

    @Column(name = "date")
    var date: Date ? = null
)
