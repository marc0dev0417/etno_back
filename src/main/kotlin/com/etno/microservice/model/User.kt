package com.etno.microservice.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id
    var id: UUID = UUID.randomUUID(),

    @Column(name = "username", unique = true)
    var username: String? = null,

    @Column(name = "password")
    var password: String? = null,

    //This will quit with another implementation -> https://www.baeldung.com/role-and-privilege-for-spring-security-registration
    @Column(name = "role")
    var role: String? = null
)