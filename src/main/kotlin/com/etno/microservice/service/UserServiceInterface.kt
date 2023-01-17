package com.etno.microservice.service

import com.etno.microservice.model.dto.*
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
interface UserServiceInterface {
    fun getUsers(): List<UserDTO>?
    fun signUp(userDTO: UserDTO): UserDTO?
    fun login(username: String, password: String): ResponseEntity<*>

    fun findUserByUsername(username: String): UserVillagerDTO?
    fun updateUserCredentials(name: String, username: String, password: String): UserDTO?

    //User Event -> ---------------------------------------------------------------------------
    fun addEventInUser(username: String, eventDTO: EventDTO): UserDTO?
    fun deleteEventInUser(username: String, title: String): UserDTO?
    fun addImageToEventInUser(username: String, title: String, imageName: String): UserDTO?
    fun deleteImageToEventInUser(username: String, title: String, imageName: String): UserDTO?

    //User Event Subscriptions -> -------------------------------------------------------------
    fun addSubscriptionToUser(username: String, title: String, subscriptionUserDTO: SubscriptionUserDTO): SubscriptionUserDTO?
    fun dropOutSubscription(username: String, title: String, subscriptionUserDTO: SubscriptionUserDTO): SubscriptionUserDTO?


    //User Pharmacy -> -------------------------------------------------------------------------
    fun addPharmacyInUser(username: String, pharmacyDTO: PharmacyDTO): UserDTO?
    fun deletePharmacyInUser(username: String, name: String): UserDTO?
    fun addImageToPharmacyInUser(username: String, name: String, imageName: String): UserDTO?
    fun deleteImageToPharmacyInUser(username: String, name: String, imageName: String): UserDTO?

    //User Tourism -> -------------------------------------------------------------------------
    fun addTourismInUser(username: String, tourismDTO: TourismDTO): UserDTO?
    fun deleteTourismInUser(username: String, title: String): UserDTO?
    fun addImageToTourismInUser(username: String, title: String, imageName: String): UserDTO?
    fun deleteImageToTourismInUser(username: String, title: String, imageName: String): UserDTO?

    //User Death -> ---------------------------------------------------------------------------
    fun addDeathInUser(username: String, deathDTO: DeathDTO): UserDTO?
    fun deleteDeathInUser(username: String, name: String): UserDTO?
    fun addImageToDeathInUser(username: String, name: String, imageName: String): UserDTO?
    fun deleteImageToDeathInUser(username: String, name: String, imageName: String): UserDTO?

    //User Phone -> ---------------------------------------------------------------------------
    fun addPhoneInUser(username: String, phoneDTO: PhoneDTO): UserDTO?
    fun deletePhoneInUser(username: String, owner: String): UserDTO?
    fun addImageToPhoneInUser(username: String, owner: String, imageName: String): UserDTO?
    fun deleteImageToPhoneInUser(username: String, owner: String, imageName: String): UserDTO?

    //User New -> ---------------------------------------------------------------------------
    fun addNewInUser(username: String, newDTO: NewDTO): UserDTO?
    fun deleteNewInUser(username: String, title: String): UserDTO?
    fun addImageToNewInUser(username: String, title: String, imageName: String): UserDTO?
    fun deleteImageToNewInUser(username: String, title: String, imageName: String): UserDTO?

    //User incidents -> ---------------------------------------------------------------------
    fun addIncidentInUser(username: String, incidentDTO: IncidentDTO): UserDTO?
}