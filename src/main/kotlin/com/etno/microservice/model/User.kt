package com.etno.microservice.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    var id: UUID = UUID.randomUUID(),

    @Column(name = "username", unique = true)
    var username: String? = null,

    @Column(name = "password")
    var password: String? = null,

    @Column(name = "role")
    var role: String? = null,
)