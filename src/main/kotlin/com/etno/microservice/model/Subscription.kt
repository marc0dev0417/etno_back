package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "subscriptions")
data class Subscription(
    @Id
    @Type(type = "uuid-char")
    var idSectionSubscribe: UUID? = UUID.randomUUID(),

    @Column(name = "token", unique = true)
    var token: String ? = null,

    @Column(name = "category")
    var category: String ? = null,

    @Column(name = "title", unique = true)
    var title: String ? = null,

    @Column(name = "isSubscribe")
    var isSubscribe: Boolean ? = null
)