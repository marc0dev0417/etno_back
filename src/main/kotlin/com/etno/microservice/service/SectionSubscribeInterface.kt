package com.etno.microservice.service

import com.etno.microservice.model.dto.SectionSubscribeDTO

interface SectionSubscribeInterface {
   fun getSectionSubscribe(): List<SectionSubscribeDTO>?
}