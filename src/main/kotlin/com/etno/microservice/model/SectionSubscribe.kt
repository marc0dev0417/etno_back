package com.etno.microservice.model

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import org.hibernate.annotations.Type
import javax.persistence.Column

@Entity
@Table(name = "sections_subscribe")
data class SectionSubscribe(
    @Id
    @Type(type = "uuid-char")
    var idSectionSubscribe: UUID = UUID.randomUUID(),

    @Column(name = "category")
    var category: String? = null,

    @Column(name = "title")
    var title: String? = null,

    @Column(name = "subscribe")
    var isSubscribe: Boolean? = null
)
