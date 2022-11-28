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

    @Column(name = "title")
    var title: String? = null,

    @Column(name = "address")
    var address: String? = null,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "organization")
    var organization: String? = null,

    @Column(name = "link")
    var link: String? = null,

    @Column(name = "startDate")
    var startDate: String? = null,

    @Column(name = "endDate")
    var endDate: String? = null,

    @Column(name = "publicationDate")
    var publicationDate: String? = null,

    @Column(name = "time")
    var time: String? = null,

    @Column(name = "latitude")
    var lat: String? = null,

    @Column(name = "longitude")
    var long: String? = null,

    @Column(name = "subscription")
    var subscription: Boolean? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var images: MutableList<Image>? = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var videos: MutableList<Video>? = mutableListOf()
)
