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

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var events: MutableList<Event>? = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var pharmacies: MutableList<Pharmacy>? = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var tourism: MutableList<Tourism> ? = mutableListOf()
)