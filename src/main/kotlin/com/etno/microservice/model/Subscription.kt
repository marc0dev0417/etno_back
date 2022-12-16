package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "subscriptions")
data class Subscription(
    @Id
    @Type(type = "uuid-char")
    var idSectionSubscribe: UUID? = UUID.randomUUID(),

    @Column(name = "category")
    var category: String ? = null,

    @Column(name = "title", unique = true)
    var title: String ? = null,

    @Column(name = "isSubscribe")
    var isSubscribe: Boolean ? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var subscriptionsUsers: MutableList<SubscriptionUser> ? = mutableListOf()
)