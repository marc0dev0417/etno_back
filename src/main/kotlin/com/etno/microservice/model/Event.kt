package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "events")
data class Event(
    @Id
    @Type(type = "uuid-char")
    var idEvent: UUID? = UUID.randomUUID(),

    @Column(name = "username")
    var username: String ? = null,

    @Column(name = "title")
    var title: String? = null,

    @Column(name = "address")
    var address: String? = null,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "organization")
    var organization: String? = null,

    @Column(name = "reservePrice")
    var reservePrice: Double ? = null,

    @Column(name = "seats")
    var seats: Int ? = null,

    @Column(name = "capacity")
    var capacity: Int ? = null,

    @Column(name = "link")
    var link: String? = null,

    @Column(name = "imageUrl")
    var imageUrl: String ? = null,

    @Column(name = "startDate")
    var startDate: String? = null,

    @Column(name = "endDate")
    var endDate: String? = null,

    @Column(name = "publicationDate")
    var publicationDate: Date? = null,

    @Column(name = "time")
    var time: String? = null,

    @Column(name = "latitude")
    var lat: String? = null,

    @Column(name = "longitude")
    var long: String? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var images: MutableList<Image>? = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var videos: MutableList<Video>? = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var userSubscriptions: MutableList<SubscriptionUser>? = mutableListOf()
)