package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "sponsors")
data class Sponsor(
    @Id
    @Type(type = "uuid-char")
    var idSponsor: UUID ? = null,

    @Column(name = "username")
    var username: String ? = null,

    @Column(name = "title")
    var title: String ? = null,

    @Column(name = "description")
    var description: String ? = null,

    @Column(name = "phone")
    var phone: String ? = null,

    @Column(name = "urlImage")
    var urlImage: String ? = null
)