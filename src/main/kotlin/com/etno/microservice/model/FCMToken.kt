package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "FCMTokens")
data class FCMToken(
    @Id
    @Type(type = "uuid-char")
    var idFMC: UUID? = UUID.randomUUID(),

    @Column(name = "token", unique = true)
    var token: String? = null
)
