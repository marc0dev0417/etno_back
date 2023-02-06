package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "links")
data class Link(
    @Id
    @Type(type = "uuid-char")
    var idLink: UUID? = null,

    @Column(name = "username")
    var username: String ? = null,

    @Column(name = "title", nullable = false)
    var title: String ? = null,

    @Column(name = "url", nullable = false)
    var url: String ? = null
)
