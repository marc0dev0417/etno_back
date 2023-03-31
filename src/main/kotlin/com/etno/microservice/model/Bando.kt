package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "bandos")
data class Bando(
    @Id
    @Type(type = "uuid-char")
    var idBando: UUID ? = UUID.randomUUID(),

    @Column(name = "username")
    var username: String ? = null,

    @Column(name = "title")
    var title: String ? = null,

    @Column(name = "description", columnDefinition = "TEXT")
    var description: String ? = null,

    @Column(name = "issuedDate")
    var issuedDate: Date ? = null,

    @Column(name = "imageUrl")
    var imageUrl: String ? = null
)
