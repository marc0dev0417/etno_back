package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tourism")
data class Tourism(
    @Id
    @Type(type = "uuid-char")
    var idTourism: UUID? = UUID.randomUUID(),

    @Column(name = "title")
    var title: String? = null,

    @Column(name = "description")
    var description: String? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var images: MutableList<Image>? = mutableListOf(),

    @Column(name = "latitude")
    var latitude: String? = null,

    @Column(name = "longitude")
    var longitude: String? = null
)
