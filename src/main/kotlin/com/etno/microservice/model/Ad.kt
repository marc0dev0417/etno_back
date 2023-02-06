package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ads")
data class Ad(
    @Id
    @Type(type = "uuid-char")
    var idAd: UUID ? = UUID.randomUUID(),

    @Column(name = "username")
    var username: String ? = null,

    @Column(name = "title")
    var title: String ? = null,

    @Column(name = "description")
    var description: String ? = null,

    @Column(name = "imageUrl")
    var imageUrl: String ? = null,

    @Column(name = "webUrl")
    var webUrl: String ? = null
)
