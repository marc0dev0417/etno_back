package com.etno.microservice.model

import org.hibernate.annotations.Type
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "videos")
data class Video(
    @Id
    @Type(type = "uuid-char")
    var idVideo: UUID = UUID.randomUUID(),

    @Column(name = "link")
    var link: String? = null
)
