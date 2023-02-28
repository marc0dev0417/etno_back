package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
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

    @Column(name = "locality")
    var locality: String ? = null,

    @Column(name = "section")
    var section: String ? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "category")
    var category: String ? = null,

    @Column(name = "link")
    var link: String? = null
)