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

    //This will quit with another implementation -> https://www.baeldung.com/role-and-privilege-for-spring-security-registration
    @Column(name = "role")
    var role: String? = null,


    /*
    @OneToMany(targetEntity = Event::class, cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_fk", referencedColumnName = "id")
    var list: MutableList<Event>? = mutableListOf()


     */
    //var listEvents: MutableList<Event>? = mutableListOf()
)