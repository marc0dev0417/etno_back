package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "subscriptions_users")
data class SubscriptionUser(
    @Id
    @Type(type = "uuid-char")
    var idSubscriptionUser: UUID ? = UUID.randomUUID(),

    @Column(name = "fcmToken")
    var fcmToken: String ? = null,

    @Column(name = "title")
    var title: String ? = null,

    @Column(name = "name")
    var name: String ? = null,

    @Column(name = "mail")
    var mail: String ? = null,

    @Column(name = "phone")
    var phone: String ? = null,

    @Column(name = "wallet")
    var wallet: Double ? = null,

    @Column(name = "isSubscribe")
    var isSubscribe: Boolean ? = null
)
