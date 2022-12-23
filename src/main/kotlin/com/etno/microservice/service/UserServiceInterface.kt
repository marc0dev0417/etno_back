package com.etno.microservice.service

import com.etno.microservice.model.dto.EventDTO
import com.etno.microservice.model.dto.PharmacyDTO
import com.etno.microservice.model.dto.TourismDTO
import com.etno.microservice.model.dto.UserDTO
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
interface UserServiceInterface {
    fun getUsers(): List<UserDTO>?
    fun signUp(userDTO: UserDTO): UserDTO?
    fun login(username: String, password: String): ResponseEntity<*>
    fun updateUserCredentials(name: String, username: String, password: String): UserDTO?

    //User Event -> ---------------------------------------------------------------------------
    fun addEventInUser(username: String, eventDTO: EventDTO): UserDTO?
    fun deleteEventInUser(username: String, title: String): UserDTO?
    fun addImageToEventInUser(username: String, title: String, imageName: String): UserDTO?
    fun deleteImageToEventInUser(username: String, title: String, imageName: String): UserDTO?

    //User Pharmacy -> -------------------------------------------------------------------------
    fun addPharmacyInUser(username: String, pharmacyDTO: PharmacyDTO): UserDTO?
    fun deletePharmacyInUser(username: String, name: String): UserDTO?
    fun addImageToPharmacyInUser(username: String, name: String, imageName: String): UserDTO?
    fun deleteImageToPharmacyInUser(username: String, name: String, imageName: String): UserDTO?

    //User Tourism -> -------------------------------------------------------------------------
    fun addTourismInUser(username: String, tourismDTO: TourismDTO): UserDTO?
    fun deleteTourismInUser(username: String, title: String, imageName: String): UserDTO?
    fun addImageToTourismInUser(username: String, title: String, imageName: String): UserDTO?
    fun deleteImageToTourismInUser(username: String, title: String, imageName: String): UserDTO?
}