package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.UserControllerInterface
import com.etno.microservice.model.dto.*
import com.etno.microservice.service.implementation.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class UserController(
    private val userService: UserService
):UserControllerInterface {
    override fun getUsers(): ResponseEntity<List<UserDTO>>? {
        return ResponseEntity.ok().body(userService.getUsers())
    }

    override fun saveUser(userDTO: UserDTO): ResponseEntity<UserDTO>? {
        return ResponseEntity.ok().body(userService.signUp(userDTO))
    }

    override fun login(username: String, password: String): ResponseEntity<*>? {
        return userService.login(username, password)
    }

    override fun findUserByUsernameToVillager(username: String): ResponseEntity<UserVillagerDTO> {
        return ResponseEntity.ok().body(userService.findUserByUsername(username))
    }

    override fun updateUser(name: String, username: String, password: String): ResponseEntity<UserDTO>? {
        return ResponseEntity.ok().body(userService.updateUserCredentials(name, username, password))
    }

    override fun addEventInUser(username: String, eventDTO: EventDTO): ResponseEntity<UserDTO>? {
        return ResponseEntity.ok().body(userService.addEventInUser(username, eventDTO))
    }

    override fun updateEventInUser(username: String, eventId: UUID, eventDTO: EventDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.updatedEventInUser(username, eventId, eventDTO))
    }


    override fun addImageToEventInUser(username: String, title: String, image: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addImageToEventInUser(username, title, image))
    }

    override fun deleteEventInUser(username: String, title: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteEventInUser(username, title))
    }

    override fun deleteImageToEventInUser(username: String, title: String, imageName: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteImageToEventInUser(username, title, imageName))
    }

    override fun addSubscriptionToUser(username: String, title: String, subscriptionUserDTO: SubscriptionUserDTO): ResponseEntity<SubscriptionUserDTO> {
        return ResponseEntity.ok().body(userService.addSubscriptionToUser(username, title, subscriptionUserDTO))
    }

    override fun dropOutSubscription(
        username: String,
        title: String,
        fcmToken: String
    ): ResponseEntity<SubscriptionUserDTO> {
        return ResponseEntity.ok().body(userService.dropOutSubscription(username, title, fcmToken))
    }

    override fun addPharmacyInUser(username: String, pharmacyDTO: PharmacyDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addPharmacyInUser(username, pharmacyDTO))
    }

    override fun deletePharmacyInUser(username: String, name: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deletePharmacyInUser(username, name))
    }

    override fun addImageToPharmacyInUser(username: String, name: String, imageName: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addImageToPharmacyInUser(username, name, imageName))
    }

    override fun deleteImageToPharmacyInUser(
        username: String,
        name: String,
        imageName: String
    ): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteImageToPharmacyInUser(username, name, imageName))
    }

    override fun addTourismInUser(username: String, tourismDTO: TourismDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addTourismInUser(username, tourismDTO))
    }

    override fun addImageToTourismInUser(username: String, title: String, imageName: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addImageToTourismInUser(username, title, imageName))
    }

    override fun deleteTourismInUser(username: String, title: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteTourismInUser(username, title))
    }

    override fun deleteImageToTourismInUser(
        username: String,
        title: String,
        imageName: String
    ): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteImageToTourismInUser(username, title, imageName))
    }

    override fun addDeathInUser(username: String, deathDTO: DeathDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addDeathInUser(username, deathDTO))
    }

    override fun deleteDeathInUser(username: String, name: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteDeathInUser(username, name))
    }

    override fun addImageToDeathInUser(username: String, name: String, imageName: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addImageToDeathInUser(username, name, imageName))
    }

    override fun deleteImageToDeathInUser(username: String, name: String, imageName: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteImageToDeathInUser(username, name, imageName))
    }

    override fun addServiceInUser(username: String, serviceDTO: ServiceDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addServiceInUser(username, serviceDTO))
    }

    override fun deleteServiceInUser(username: String, owner: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteServiceInUser(username, owner))
    }

    override fun addImageToServiceInUser(username: String, owner: String, imageName: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addImageToServiceInUser(username, owner, imageName))
    }

    override fun deleteImageToServiceInUser(username: String, owner: String, imageName: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteImageToServiceInUser(username, owner, imageName))
    }

    override fun addNewsInUser(username: String, newsDTO: NewsDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addNewsInUser(username, newsDTO))
    }

    override fun deleteNewsInUser(username: String, title: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteNewsInUser(username, title))
    }

    override fun addImageToNewsInUser(username: String, title: String, imageName: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addImageToNewsInUser(username, title, imageName))
    }

    override fun deleteImageToNewsInUser(username: String, title: String, imageName: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteImageToNewsInUser(username, title, imageName))
    }

    override fun addIncidentInUser(username: String, incidentDTO: IncidentDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addIncidentInUser(username, incidentDTO))
    }

    override fun addBandoInUser(username: String, bandoDTO: BandoDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addBandoInUser(username, bandoDTO))
    }

    override fun deleteBandoInUser(username: String, title: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteBandoInUser(username, title))
    }

    override fun addImageToBandoInUser(username: String, title: String, imageName: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addImageToBandoInUser(username, title, imageName))
    }

    override fun deleteImageToBandoInUser(username: String, title: String, imageName: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteImageToBandoInUser(username, title, imageName))
    }

    override fun addLinkInUser(username: String, linkDTO: LinkDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addLinkInUser(username, linkDTO))
    }

    override fun deleteLinkInUser(username: String, idLink: UUID): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteLinkInUser(username, idLink))
    }

    override fun addSponsorInUser(username: String, sponsorDTO: SponsorDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addSponsorInUser(username, sponsorDTO))
    }

    override fun deleteSponsorInUser(username: String, title: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteSponsorInUser(username, title))
    }

    override fun addImageToSponsorInUser(username: String, title: String, imageName: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addImageToSponsorInUser(username, title, imageName))
    }

    override fun deleteImageToSponsorInUser(username: String, title: String, imageName: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteImageToSponsorInUser(username, title, imageName))
    }

    override fun addAdInUser(username: String, adDTO: AdDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.addAdInUser(username, adDTO))
    }

    override fun deleteAdInUser(username: String, title: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.deleteAdInUser(username, title))
    }

    override fun updatePharmacyInUser(username: String, pharmacyId: UUID, pharmacyDTO: PharmacyDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.updatePharmacyInUser(username, pharmacyId, pharmacyDTO))
    }

    override fun updateTourismInUser(
        username: String,
        tourismId: UUID,
        tourismDTO: TourismDTO
    ): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.updateTourismInUser(username, tourismId, tourismDTO))
    }

    override fun updateBandoInUser(username: String, bandoId: UUID, bandoDTO: BandoDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.updateBandoInUser(username, bandoId, bandoDTO))
    }

    override fun updateDeathInUser(username: String, deathId: UUID, deathDTO: DeathDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.updateDeathInUser(username, deathId, deathDTO))
    }

    override fun updateServiceInUser(
        username: String,
        serviceId: UUID,
        serviceDTO: ServiceDTO
    ): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.updateServiceInUser(username, serviceId, serviceDTO))
    }

    override fun updateNewsInUser(username: String, newsId: UUID, newsDTO: NewsDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.updateNewsInUser(username, newsId, newsDTO))
    }

    override fun updateLinkInUser(username: String, linkId: UUID, linkDTO: LinkDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.updateLinkInUser(username, linkId, linkDTO))
    }

    override fun updateSponsorInUser(username: String, sponsorId: UUID, sponsorDTO: SponsorDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.updateSponsorInUser(username, sponsorId, sponsorDTO))
    }

    override fun updateAdInUser(username: String, adId: UUID, adDTO: AdDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.updateAdInUser(username, adId, adDTO))
    }
}