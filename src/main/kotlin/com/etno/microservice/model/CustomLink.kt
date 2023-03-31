package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "custom_links")
data class CustomLink(
    @Id
    @Type(type = "uuid-char")
    var idCustomLink: UUID? = UUID.randomUUID(),

    @Column(name = "username")
    var username: String ? = null,

    @Column(name = "name")
    var name: String ? = null,

    @Column(name = "webUlr")
    var webUrl: String ? = null,

    @Column(name = "iconName")
    var iconName: String ? = null
)
