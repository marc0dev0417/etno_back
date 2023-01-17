package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "incidents")
data class Incident(
    @Id
    @Type(type = "uuid-char")
    var idIncidence: UUID ? = UUID.randomUUID(),

    @Column(name = "username")
    var username: String ? = null,

    @Column(name = "fcmToken")
    var fcmToken: String ? = null,

    @Column(name = "title")
    var title: String ? = null,

    @Column(name = "description")
    var description: String ? = null
)
