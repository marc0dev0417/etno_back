package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @Type(type = "uuid-char")
    var idUser:UUID? = UUID.randomUUID(),

    @Column(name = "username", unique = true, nullable = false)
    var username: String? = null,

    @Column(name = "password", nullable = false)
    var password: String? = null,

    @Column(name = "role")
    var role: String? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var events: MutableList<Event>? = mutableListOf()
)