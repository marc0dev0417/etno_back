package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SectionDetailsDTO(
    @JsonProperty("eventQuantity") var eventQuantity: Int ? = null,

    @JsonProperty("tourismQuantity") var tourismQuantity: Int ? = null,

    @JsonProperty("pharmacyQuantity") var pharmacyQuantity: Int ? = null,

    @JsonProperty("serviceQuantity") var serviceQuantity: Int ? = null,

    @JsonProperty("newsQuantity") var newsQuantity: Int ? = null,

    @JsonProperty("bandoQuantity") var bandoQuantity: Int ? = null,

    @JsonProperty("adQuantity") var adQuantity: Int ? = null,

    @JsonProperty("galleryQuantity") var galleryQuantity: Int ? = null,

    @JsonProperty("deathQuantity") var deathQuantity: Int ? = null,

    @JsonProperty("linkQuantity") var linkQuantity: Int ? = null,

    @JsonProperty("sponsorQuantity") var sponsorQuantity: Int ? = null,

    @JsonProperty("incidentQuantity") var incidentQuantity: Int ? = null,

    @JsonProperty("reserveQuantity") var reserveQuantity: Int ? = null
)