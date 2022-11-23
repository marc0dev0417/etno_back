package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "events")
data class Event(
    @Id
    @Type(type = "uuid-char")
    var idEvent: UUID = UUID.randomUUID(),

    @Column(name = "title")
    var title: String? = null,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "organization")
    var organization: String? = null,

    @Column(name = "startDate")
    var startDate: Date? = null,

    @Column(name = "endDate")
    var endDate: Date? = null,

    @Column(name = "publicationDate")
    var publicationDate: Date? = null,

    @Column(name = "latitude")
    var latitude: String? = null,

    @Column(name = "longitude")
    var longitude: String? = null,

    @Column(name = "subscription")
    var subscription: Boolean? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var images: MutableList<Image>? = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var videos: MutableList<Video>? = mutableListOf()
)
