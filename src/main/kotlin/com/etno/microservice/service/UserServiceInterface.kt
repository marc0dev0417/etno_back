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
    fun addServiceInUser(username: String, serviceDTO: ServiceDTO): UserDTO?
    fun deleteServiceInUser(username: String, owner: String): UserDTO?
    fun addImageToServiceInUser(username: String, owner: String, imageName: String): UserDTO?
    fun deleteImageToServiceInUser(username: String, owner: String, imageName: String): UserDTO?

    //User New -> ---------------------------------------------------------------------------
    fun addNewsInUser(username: String, newsDTO: NewsDTO): UserDTO?
    fun deleteNewsInUser(username: String, title: String): UserDTO?
    fun addImageToNewsInUser(username: String, title: String, imageName: String): UserDTO?
    fun deleteImageToNewsInUser(username: String, title: String, imageName: String): UserDTO?

    //User incidents -> ---------------------------------------------------------------------
    fun addIncidentInUser(username: String, incidentDTO: IncidentDTO): UserDTO?

    //User bandos -> ------------------------------------------------------------------------
    fun addBandoInUser(username: String, bandoDTO: BandoDTO): UserDTO?
    fun deleteBandoInUser(username: String, title: String): UserDTO?
    fun addImageToBandoInUser(username: String, title: String, imageName: String): UserDTO?
    fun deleteImageToBandoInUser(username: String, title: String, imageName: String): UserDTO?

    //User links -> -------------------------------------------------------------------------
    fun addLinkInUser(username: String, linkDTO: LinkDTO): UserDTO?
    fun deleteLinkInUser(username: String, title: String): UserDTO?



    //User sponsors -> ----------------------------------------------------------------------
    fun addSponsorInUser(username: String, sponsorDTO: SponsorDTO): UserDTO?
    fun deleteSponsorInUser(username: String, title: String): UserDTO?
    fun addImageToSponsorInUser(username: String, title: String, imageName: String): UserDTO?
    fun deleteImageToSponsorInUser(username: String, title: String, imageName: String): UserDTO?

    //User Ad -> ----------------------------------------------------------------------------
    fun addAdInUser(username: String, adDTO: AdDTO): UserDTO?
    fun deleteAdInUser(username: String, title: String): UserDTO?
}