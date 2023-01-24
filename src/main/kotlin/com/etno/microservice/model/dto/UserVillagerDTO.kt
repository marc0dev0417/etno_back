package com.etno.microservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserVillagerDTO(
    @JsonProperty("username") var username: String ? = null,

    @JsonProperty("events") var events: MutableList<EventDTO> ? = null,

    @JsonProperty("pharmacies") var pharmacies: MutableList<PharmacyDTO> ? = mutableListOf(),

    @JsonProperty("tourism") var tourism: MutableList<TourismDTO> ? = mutableListOf(),

    @JsonProperty("deaths") var deaths: MutableList<DeathDTO> ? = mutableListOf(),

    @JsonProperty("phones") var phones: MutableList<PhoneDTO> ? = mutableListOf(),

    @JsonProperty("news") var news: MutableList<NewDTO> ? = mutableListOf(),

    @JsonProperty("incidents") var incidents: MutableList<IncidentDTO> ? = mutableListOf(),

    @JsonProperty("bandos") var bandos: MutableList<BandoDTO> ? = mutableListOf(),

    @JsonProperty("links") var links: MutableList<LinkDTO> ? = mutableListOf(),

    @JsonProperty("sponsors") var sponsors: MutableList<SponsorDTO> ? = mutableListOf()
)