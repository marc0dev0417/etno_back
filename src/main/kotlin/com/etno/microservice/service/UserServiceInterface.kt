package com.etno.microservice.service

import com.etno.microservice.model.dto.*
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
interface UserServiceInterface {
    fun getUsers(): List<UserDTO>?
    fun signUp(userDTO: UserDTO): UserDTO?
    fun login(username: String, password: String): ResponseEntity<*>

    fun findUserByUsername(username: String): UserVillagerDTO?
    fun updateUserCredentials(name: String, username: String, password: String): UserDTO?

    //User Event -> ---------------------------------------------------------------------------
    fun addEventInUser(username: String, eventDTO: EventDTO): UserDTO?

    fun updatedEventInUser(username: String, eventId: UUID, eventDTO: EventDTO): UserDTO?
    fun deleteEventInUser(username: String, idEvent: UUID): UserDTO?
    fun addImageToEventInUser(username: String, title: String, imageName: String): UserDTO?
    fun deleteImageToEventInUser(username: String, title: String, imageName: String): UserDTO?

    //User Event Subscriptions -> -------------------------------------------------------------
    fun addSubscriptionToUser(username: String, title: String, subscriptionUserDTO: SubscriptionUserDTO): SubscriptionUserDTO?
    fun dropOutSubscription(username: String, title: String, fcmToken: String): SubscriptionUserDTO?


    //User Pharmacy -> -------------------------------------------------------------------------
    fun updatePharmacyInUser(username: String, pharmacyId: UUID, pharmacyDTO: PharmacyDTO): UserDTO?
    fun addPharmacyInUser(username: String, pharmacyDTO: PharmacyDTO): UserDTO?
    fun deletePharmacyInUser(username: String, idPharmacy: UUID): UserDTO?
    fun addImageToPharmacyInUser(username: String, name: String, imageName: String): UserDTO?
    fun deleteImageToPharmacyInUser(username: String, name: String, imageName: String): UserDTO?

    //User Tourism -> -------------------------------------------------------------------------
    fun updateTourismInUser(username: String, tourismId: UUID, tourismDTO: TourismDTO): UserDTO?
    fun addTourismInUser(username: String, tourismDTO: TourismDTO): UserDTO?
    fun deleteTourismInUser(username: String, idTourism: UUID): UserDTO?
    fun addImageToTourismInUser(username: String, title: String, imageName: String): UserDTO?
    fun deleteImageToTourismInUser(username: String, title: String, imageName: String): UserDTO?

    //User Death -> ---------------------------------------------------------------------------
    fun updateDeathInUser(username: String, deathId: UUID, deathDTO: DeathDTO): UserDTO?
    fun addDeathInUser(username: String, deathDTO: DeathDTO): UserDTO?
    fun deleteDeathInUser(username: String, idDeath: UUID): UserDTO?
    fun addImageToDeathInUser(username: String, name: String, imageName: String): UserDTO?
    fun deleteImageToDeathInUser(username: String, name: String, imageName: String): UserDTO?

    //User Phone -> ---------------------------------------------------------------------------
    fun updateServiceInUser(username: String, serviceId: UUID, serviceDTO: ServiceDTO): UserDTO?
    fun addServiceInUser(username: String, serviceDTO: ServiceDTO): UserDTO?
    fun deleteServiceInUser(username: String, idService: UUID): UserDTO?
    fun addImageToServiceInUser(username: String, owner: String, imageName: String): UserDTO?
    fun deleteImageToServiceInUser(username: String, owner: String, imageName: String): UserDTO?

    //User New -> ---------------------------------------------------------------------------
    fun updateNewsInUser(username: String, newsId: UUID, newsDTO: NewsDTO): UserDTO?
    fun addNewsInUser(username: String, newsDTO: NewsDTO): UserDTO?
    fun deleteNewsInUser(username: String, idNews: UUID): UserDTO?
    fun addImageToNewsInUser(username: String, title: String, imageName: String): UserDTO?
    fun deleteImageToNewsInUser(username: String, title: String, imageName: String): UserDTO?

    //User incidents -> ---------------------------------------------------------------------
    fun addIncidentInUser(username: String, incidentDTO: IncidentDTO): UserDTO?
    fun solveIncidence(username: String, incidentId: UUID, solution: String): UserDTO?

    //User bandos -> ------------------------------------------------------------------------
    fun updateBandoInUser(username: String, bandoId: UUID, bandoDTO: BandoDTO): UserDTO?
    fun addBandoInUser(username: String, bandoDTO: BandoDTO): UserDTO?
    fun deleteBandoInUser(username: String, idBando: UUID): UserDTO?
    fun addImageToBandoInUser(username: String, title: String, imageName: String): UserDTO?
    fun deleteImageToBandoInUser(username: String, title: String, imageName: String): UserDTO?

    //User links -> -------------------------------------------------------------------------
    fun updateLinkInUser(username: String, linkId: UUID, linkDTO: LinkDTO): UserDTO?
    fun addLinkInUser(username: String, linkDTO: LinkDTO): UserDTO?
    fun deleteLinkInUser(username: String, idLink: UUID): UserDTO?


    //User sponsors -> ----------------------------------------------------------------------
    fun updateSponsorInUser(username: String, sponsorId: UUID, sponsorDTO: SponsorDTO): UserDTO?
    fun addSponsorInUser(username: String, sponsorDTO: SponsorDTO): UserDTO?
    fun deleteSponsorInUser(username: String, idSponsor: UUID): UserDTO?
    fun addImageToSponsorInUser(username: String, title: String, imageName: String): UserDTO?
    fun deleteImageToSponsorInUser(username: String, title: String, imageName: String): UserDTO?


    //User Ad -> ----------------------------------------------------------------------------
    fun updateAdInUser(username: String, adId: UUID, adDTO: AdDTO): UserDTO?
    fun addAdInUser(username: String, adDTO: AdDTO): UserDTO?
    fun deleteAdInUser(username: String, idAd: UUID): UserDTO?


    //User reserve -> -----------------------------------------------------------------------
    fun addReserveInUser(username: String, reserveDTO: ReserveDTO, idPlace: UUID, idHall: UUID): UserDTO?
    fun addReserveDataUser(username: String, reserveName: String, reserveUserDTO: ReserveUserDTO): UserDTO?
    fun deleteReserveFromUser(username: String, idReserve: UUID) : UserDTO?
    fun confirmReserve(username: String, idReserve: UUID): UserDTO?

    fun addPlaceInUser(username: String, placeDTO: PlaceDTO) : UserDTO?
    fun deletePlaceFromUser(username : String, idReserve: UUID) : UserDTO?
    fun updatePlaceInUser(username : String, placeId: UUID, placeDTO: PlaceDTO) : UserDTO?


    //User sectionDetails -> ------------------------------------------------------------------
    fun getSectionDetails(username: String): SectionDetailsDTO?


    //User Link Custom -> ---------------------------------------------------------------------
    fun addLinkCustomInUser(username: String, customLinkDTO: CustomLinkDTO): UserDTO?
    fun updateLinkCustomInUser(username: String, idCustomLink: UUID, customLinkDTO: CustomLinkDTO): UserDTO?
    fun deleteLinkCustomInUser(username: String, idCustomLink: UUID): UserDTO?


    //User Quiz ->
    fun addQuizInUser(username: String, quizDTO: QuizDTO): UserDTO?
    fun updateQuizInUser(username: String, idQuiz: UUID, quizDTO: QuizDTO): UserDTO?
    fun updateResultQuizInUser(username: String, idQuiz: UUID, option: Int): UserDTO?
    fun deleteQuizFromUser(username: String, idQuiz: UUID): UserDTO?
}