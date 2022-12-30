package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "news")
data class New(
    @Id
    @Type(type = "uuid-char")
    var idNew: UUID ? = UUID.randomUUID(),

    @Column(name = "username")
    var username: String ? = null,

    @Column(name = "category")
    var category: String ? = null,

    @Column(name = "title")
    var title: String ? = null,

    @Column(name = "publicationDate")
    var publicationDate: String ? = null,

    @Column(name = "description", columnDefinition = "TEXT")
    var description: String ? = null,

    @Column(name = "imageUrl")
    var imageUrl: String ? = null
)