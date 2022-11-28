package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "images")
data class Image(
    @Id
    @Type(type = "uuid-char")
    var idImage: UUID? = UUID.randomUUID(),

    @Column(name = "name", unique = true)
    var name: String? = null,

    @Column(name = "link")
    var link: String? = null
)