package com.etno.microservice.service

import com.etno.microservice.model.dto.FestivityDTO
import org.springframework.stereotype.Service

@Service
interface FestivityServiceInterface {
    fun getFestivities(): List<FestivityDTO>?
    fun saveFestivity(festivityDTO: FestivityDTO): FestivityDTO?
    fun deleteFestivityByTitle(title: String): FestivityDTO?
    fun addImageToFestivity(title: String, name: String): FestivityDTO?
    fun deleteImageFromFestivity(title: String, name: String): FestivityDTO?
}