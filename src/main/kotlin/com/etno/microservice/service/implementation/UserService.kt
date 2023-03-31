package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.*
import com.etno.microservice.repository.*
import com.etno.microservice.security.JwtTokenUtil
import com.etno.microservice.service.UserServiceInterface
import com.etno.microservice.service.implementation.jwt.JwtUserDetailsService
import com.etno.microservice.util.DataConverter
import com.etno.microservice.util.Urls
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.text.DateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters.lastDayOfYear
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val eventRepository: EventRepository,
    private val subscriptionUser: SubscriptionUserRepository,
    private val imageRepository: ImageRepository,
    private val pharmacyRepository: PharmacyRepository,
    private val pharmacyDateRepository: PharmacyDateRepository,
    private val tourismRepository: TourismRepository,
    private val deathRepository: DeathRepository,
    private val serviceRepository: ServiceRepository,
    private val newsRepository: NewsRepository,
    private val bandoRepository: BandoRepository,
    private val fcmTokenRepository: FCMTokenRepository,
    private val incidentRepository: IncidenceRepository,
    private val linkRepository: LinkRepository,
    private val sponsorRepository: SponsorRepository,
    private val adRepository: AdRepository,
    private val reserveRepository: ReserveRepository,
    private val placeRepository: PlaceRepository,
    private val hallRepository: HallRepository,
    private val quizRepository: QuizRepository,
    private val customLinkRepository: CustomLinkRepository,
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: JwtUserDetailsService,
    private val jwtTokenUtil: JwtTokenUtil
): UserServiceInterface {

    override fun getUsers(): List<UserDTO>? {
        return userRepository.findAll().map { DataConverter.userToDTO(it) }
    }

    override fun signUp(userDTO: UserDTO): UserDTO? {
        val userItem = DataConverter.userFromDTO(userDTO)
        userItem.idUser = UUID.randomUUID()
        userItem.password = BCryptPasswordEncoder().encode(userItem.password)
        val userToSave = userRepository.save(userItem)
        return DataConverter.userToDTO(userToSave)
    }

    override fun login(username: String, password: String): ResponseEntity<*> {
        val responseMap: MutableMap<String, Any> = mutableMapOf()
        val user = userRepository.findUserByUsername(username)
        val dataFormatMedium = DateFormat.getDateInstance(DateFormat.MEDIUM)

        try {
            val auth: Authentication =
                authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(
                        username,
                        password
                    )
                )
            if (auth.isAuthenticated) {
                val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
                val token: String = jwtTokenUtil.generateToken(userDetails) ?: ""
                responseMap["error"] = false
                responseMap["message"] = "Logged In"
                responseMap["token"] = token
                responseMap["token_expired"] = jwtTokenUtil.isTokenExpired(token).toString()
                responseMap["expired_date"] =
                    dataFormatMedium.format(jwtTokenUtil.getExpirationDateFromToken(token)).toString()
                responseMap["user"] = DataConverter.userLoginToDTO(user!!)

                return ResponseEntity.ok(responseMap)
            } else {
                responseMap["error"] = true
                responseMap["message"] = "Invalid Credentials"
                return ResponseEntity.status(401).body(responseMap)
            }

        } catch (e: DisabledException) {
            e.printStackTrace();
            responseMap["error"] = true
            responseMap["message"] = "User is disabled"
            return ResponseEntity.status(500).body(responseMap);
        } catch (e: BadCredentialsException) {
            responseMap["error"] = true
            responseMap["message"] = "Invalid Credentials"
            return ResponseEntity.status(401).body(responseMap);
        } catch (e: Exception) {
            e.printStackTrace();
            responseMap["error"] = true
            responseMap["message"] = "Something went wrong";
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    override fun findUserByUsername(username: String): UserVillagerDTO? {
        return DataConverter.userVillagerToDTO(userRepository.findUserByUsername(username)!!)
    }

    override fun updateUserCredentials(name: String, username: String, password: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(name)
        itemUser?.username = username
        itemUser?.password = BCryptPasswordEncoder().encode(password)
        val itemUserSaved = userRepository.save(itemUser!!)

        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun addEventInUser(username: String, eventDTO: EventDTO): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        eventDTO.username = itemUser?.username
        eventDTO.seats = eventDTO.capacity
            val itemEventSaved = eventRepository.save(DataConverter.eventFromDTO(eventDTO))
            itemUser?.events?.add(DataConverter.eventFromDTO(DataConverter.eventToDTO(itemEventSaved)))
            userRepository.save(itemUser!!)
            /*
            if (fcmTokenRepository.findAll().any { it.username == username }){
                val restTemplate = RestTemplate()
                val map: Map<String, String> = mapOf("subject" to "Nuevo evento", "content" to "Evento ${itemEventSaved.title} disponible", "username" to "${itemUser.username}")

                val response: ResponseEntity<Void> = restTemplate.postForEntity(Urls.urlSendNotification, map, Void::class.java)
            }
             */
        return DataConverter.userToDTO(itemUser)
    }

    override fun updatedEventInUser(username: String, eventId: UUID ,eventDTO: EventDTO): UserDTO? {
        var userItem = userRepository.findUserByUsername(username)
        val eventFound = userItem?.events?.find { it.idEvent ==  eventId}
        if (eventFound != DataConverter.eventFromDTO(eventDTO)){
            eventDTO.idEvent = eventId
            eventDTO.username = username

            userItem?.events?.set(userItem.events!!.indexOf(eventFound!!), DataConverter.eventFromDTO(eventDTO))
             userItem = userRepository.save(userItem!!)
        }
        return DataConverter.userToDTO(userItem!!)
    }

    override fun deleteEventInUser(username: String, idEvent: UUID): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        itemUser?.events?.removeIf { it.idEvent == idEvent }
        val itemSaved = userRepository.save(itemUser!!)
        eventRepository.deleteById(idEvent)
        return DataConverter.userToDTO(itemSaved)
    }

    override fun addImageToEventInUser(username: String, title: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemEvent = eventRepository.findEventByTitleAndUsername(title, username)
        val itemImage = imageRepository.findImageByName(imageName)

        itemEvent?.imageUrl = itemImage?.link
        itemUser?.events?.find { it.title == itemEvent?.title }?.images?.add(itemImage!!)
        val itemUserSaved = userRepository.save(itemUser!!)

        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun deleteImageToEventInUser(username: String, title: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemEvent = eventRepository.findEventByTitleAndUsername(title, username)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.events?.find{ it.title == itemEvent?.title }?.images?.remove(itemImage)
        val itemUserSaved = userRepository.save(itemUser!!)
        imageRepository.delete(itemImage!!)

        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun addSubscriptionToUser(
        username: String,
        title: String,
        subscriptionUserDTO: SubscriptionUserDTO
    ): SubscriptionUserDTO {
        var itemUser = userRepository.findUserByUsername(username)
        val itemEvent = eventRepository.findEventByTitleAndUsername(title, username)
        val subscriptionUser = subscriptionUser.findSubscriptionByFcmTokenAndTitle(subscriptionUserDTO.fcmToken!!, title)
        subscriptionUserDTO.title = title
        subscriptionUserDTO.isSubscribe = true

        if(subscriptionUser == null){
            when(itemEvent?.seats){
                in 1 .. itemEvent?.capacity!! -> {
                    itemEvent.seats = (itemEvent.seats!! - 1)
                    subscriptionUserDTO.seats = itemEvent.seats
                    itemUser?.events?.find { it.title == title }?.userSubscriptions?.add(
                        DataConverter.subscriptionUserFromDTO(subscriptionUserDTO)
                    )
                    itemUser = userRepository.save(itemUser!!)
                }
            }
        }else{
            when(itemEvent?.seats){
                in 1 .. itemEvent?.capacity!! -> {
                    itemEvent.seats = (itemEvent.seats!! - 1)
                    subscriptionUserDTO.seats = itemEvent.seats
                    itemUser?.events?.find { it.title == title }?.userSubscriptions?.find { it.fcmToken == subscriptionUserDTO.fcmToken }.also {
                        it?.title = subscriptionUserDTO.title
                        it?.name = subscriptionUserDTO.name
                        it?.seats = subscriptionUserDTO.seats
                        it?.phone = subscriptionUserDTO.phone
                        it?.wallet = subscriptionUserDTO.wallet
                        it?.isSubscribe = subscriptionUserDTO.isSubscribe
                    }
                    itemUser = userRepository.save(itemUser!!)
                }
            }
        }
        val findEvent = itemUser?.events?.find { it.title == title }
        val findUserSubscription = findEvent?.userSubscriptions?.find { it.fcmToken == subscriptionUserDTO.fcmToken }
        return DataConverter.subscriptionUserToDTO(findUserSubscription!!)
    }

    override fun dropOutSubscription(username: String, title: String, fcmToken: String): SubscriptionUserDTO? {
        var itemUser = userRepository.findUserByUsername(username)
        val itemEvent = eventRepository.findEventByTitleAndUsername(title, username)
        val subscriptionUser =
            subscriptionUser.findSubscriptionByFcmTokenAndTitle(fcmToken, title)

        if (subscriptionUser != null) {
            when (itemEvent?.seats) {
                in 0..itemEvent?.capacity!! -> {
                    itemEvent.seats = (itemEvent.seats!! + 1)

                    itemUser?.events?.find { it.title == title }?.userSubscriptions?.find { it.fcmToken == fcmToken }
                        .also {
                            it?.seats = itemEvent.seats
                            it?.isSubscribe = false
                        }
                    itemUser = userRepository.save(itemUser!!)
                }
            }
        }
        val findEvent = itemUser?.events?.find { it.title == title }
        val findUserSubscription = findEvent?.userSubscriptions?.find { it.fcmToken == fcmToken }
        return DataConverter.subscriptionUserToDTO(findUserSubscription!!)
    }

    override fun updatePharmacyInUser(username: String, pharmacyId: UUID, pharmacyDTO: PharmacyDTO): UserDTO? {
        var nowDate: Date
        val now = LocalDate.now()
        val lastDay = now.with(lastDayOfYear())
        val getDayLast = Date.from(lastDay.atStartOfDay(ZoneId.systemDefault()).toInstant())
        var userItem = userRepository.findUserByUsername(username)
        val pharmacyFound = userItem?.pharmacies?.find { it.idPharmacy == pharmacyId }
        if (pharmacyFound != DataConverter.pharmacyFromDTO(pharmacyDTO)){
            pharmacyDTO.idPharmacy = pharmacyId
            pharmacyDTO.username = username

            if(pharmacyDTO.type == "Guardia"){
                if(pharmacyDTO.durationDays != 0 || pharmacyDTO.durationDays != 0) {
                    for (index in 1..pharmacyDTO.durationDays!!) {
                        nowDate =
                            DataConverter.sumDate(pharmacyDTO.startDate!!, pharmacyDTO.frequencyInDays!! * index)!!
                        pharmacyDTO.dates?.add(
                            PharmacyDateDTO(
                                idPharmacyDate = UUID.randomUUID(),
                                username = username,
                                namePharmacy = pharmacyDTO.name,
                                date = nowDate
                            )
                        )
                        if (nowDate.after(getDayLast)) {
                            break
                        }
                    }
                    pharmacyDTO.dates?.removeLast()
                    pharmacyDateRepository.saveAll(pharmacyDTO.dates!!.map { p -> DataConverter.pharmacyDateFromDTO(p) })
                }
                userItem?.pharmacies?.set(userItem.pharmacies!!.indexOf(pharmacyFound), DataConverter.pharmacyFromDTO(pharmacyDTO))
                userItem = userRepository.save(userItem!!)
            }else{
                userItem?.pharmacies?.set(userItem.pharmacies!!.indexOf(pharmacyFound), DataConverter.pharmacyFromDTO(pharmacyDTO))
                userItem = userRepository.save(userItem!!)
            }
        }
        return DataConverter.userToDTO(userItem!!)
    }

    override fun addPharmacyInUser(username: String, pharmacyDTO: PharmacyDTO): UserDTO? {
        var nowDate: Date
        val now = LocalDate.now()
        val lastDay = now.with(lastDayOfYear())
        val getDayLast = Date.from(lastDay.atStartOfDay(ZoneId.systemDefault()).toInstant())
        var itemUser = userRepository.findUserByUsername(username)
        val itemPharmacy = pharmacyRepository.findPharmacyByNameAndUsername(pharmacyDTO.name!!, username)

            pharmacyDTO.idPharmacy = UUID.randomUUID()
            pharmacyDTO.username = itemUser?.username

            if(pharmacyDTO.type == "Guardia"){
                if(pharmacyDTO.durationDays != 0 || pharmacyDTO.durationDays != 0){
                    for (index in 1 ..pharmacyDTO.durationDays!!){
                        nowDate = DataConverter.sumDate(pharmacyDTO.startDate!!, pharmacyDTO.frequencyInDays!! * index)!!
                        pharmacyDTO.dates?.add(PharmacyDateDTO(idPharmacyDate = UUID.randomUUID(), username = username, namePharmacy = pharmacyDTO.name, date = nowDate))
                        if (nowDate.after(getDayLast)){
                            break
                        }
                    }
                    pharmacyDTO.dates?.removeLast()
                    pharmacyDateRepository.saveAll(pharmacyDTO.dates!!.map { p -> DataConverter.pharmacyDateFromDTO(p) })
                }
                itemUser?.pharmacies?.add(DataConverter.pharmacyFromDTO(pharmacyDTO))
            }else{
                itemUser?.pharmacies?.add(DataConverter.pharmacyFromDTO(pharmacyDTO))
            }
            itemUser = userRepository.save(itemUser!!)
        return DataConverter.userToDTO(itemUser)
    }

    override fun deletePharmacyInUser(username: String, idPharmacy: UUID): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        itemUser?.pharmacies?.removeIf { it.idPharmacy == idPharmacy }
        val itemSaved = userRepository.save(itemUser!!)
        pharmacyRepository.deleteById(idPharmacy)
        return DataConverter.userToDTO(itemSaved)
    }

    override fun addImageToPharmacyInUser(username: String, name: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemPharmacy = pharmacyRepository.findPharmacyByNameAndUsername(name, username)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.pharmacies?.find { it.name == itemPharmacy?.name }?.imageUrl = itemImage?.link
        val itemUserSaved = userRepository.save(itemUser!!)

        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun deleteImageToPharmacyInUser(username: String, name: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemPharmacy = pharmacyRepository.findPharmacyByNameAndUsername(name, username)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.pharmacies?.find { it.name == itemPharmacy?.name }?.imageUrl = ""
        val itemUserSaved = userRepository.save(itemUser!!)

        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun updateTourismInUser(username: String, tourismId: UUID, tourismDTO: TourismDTO): UserDTO? {
        var userItem = userRepository.findUserByUsername(username)
        val tourismFound = userItem?.tourism?.find { it.idTourism == tourismId }
        if (tourismFound != DataConverter.tourismFromDTO(tourismDTO)){
            tourismDTO.idTourism = tourismId
            tourismDTO.username = username

            userItem?.tourism?.set(userItem.tourism!!.indexOf(tourismFound!!), DataConverter.tourismFromDTO(tourismDTO))
            userItem = userRepository.save(userItem!!)
        }
        return DataConverter.userToDTO(userItem!!)
    }

    override fun addTourismInUser(username: String, tourismDTO: TourismDTO): UserDTO? {
        var itemUser = userRepository.findUserByUsername(username)

        tourismDTO.idTourism = UUID.randomUUID()
        tourismDTO.username = username
        itemUser?.tourism?.add(DataConverter.tourismFromDTO(tourismDTO))
        itemUser = userRepository.save(itemUser!!)

        return DataConverter.userToDTO(itemUser)
    }

    override fun deleteTourismInUser(username: String, idTourism: UUID): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        itemUser?.tourism?.removeIf { it.idTourism == idTourism }
        val itemUserSaved = userRepository.save(itemUser!!)
        tourismRepository.deleteById(idTourism)
        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun addImageToTourismInUser(username: String, title: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemTourism = tourismRepository.findTourismByTitleAndUsername(title, username)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.tourism?.find { it.title == itemTourism?.title }?.imageUrl = itemImage?.link
        val itemUserSaved = userRepository.save(itemUser!!)
        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun deleteImageToTourismInUser(username: String, title: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemTourism = tourismRepository.findTourismByTitleAndUsername(title, username)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.tourism?.find { it.title == itemTourism?.title }?.imageUrl = ""
        val itemUserSaved = userRepository.save(itemUser!!)
        imageRepository.delete(itemImage!!)

        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun updateDeathInUser(username: String, deathId: UUID, deathDTO: DeathDTO): UserDTO? {
        var userItem = userRepository.findUserByUsername(username)
        val deathFound = userItem?.deaths?.find { it.idDeath == deathId }
        if (deathFound != DataConverter.deathFromDTO(deathDTO)){
            deathDTO.idDeath = deathId
            deathDTO.username = username

            userItem?.deaths?.set(userItem.deaths!!.indexOf(deathFound), DataConverter.deathFromDTO(deathDTO))
            userItem = userRepository.save(userItem!!)
        }
        return DataConverter.userToDTO(userItem!!)
    }

    override fun addDeathInUser(username: String, deathDTO: DeathDTO): UserDTO? {
        var itemUser = userRepository.findUserByUsername(username)
        val itemDeath = deathRepository.findDeathByUsernameAndName(username, deathDTO.name!!)

        if (itemDeath == null){
            deathDTO.idDeath = UUID.randomUUID()
            deathDTO.username = username
            itemUser?.deaths?.add(DataConverter.deathFromDTO(deathDTO))
            itemUser = userRepository.save(itemUser!!)
        }else{
            val checkIfExistDeath = itemUser?.deaths?.find { it.name == itemDeath.name }
            if (checkIfExistDeath == null){
                deathDTO.idDeath = UUID.randomUUID()
                deathDTO.username = username
                itemUser?.deaths?.add(DataConverter.deathFromDTO(deathDTO))
                itemUser = userRepository.save(itemUser!!)
            }
        }
        return DataConverter.userToDTO(itemUser!!)
    }

    override fun deleteDeathInUser(username: String, idDeath: UUID): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        itemUser?.deaths?.removeIf { it.idDeath == idDeath }
        val itemUserSaved = userRepository.save(itemUser!!)
        deathRepository.deleteById(idDeath)
        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun addImageToDeathInUser(username: String, name: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemDeath = deathRepository.findDeathByUsernameAndName(username, name)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.deaths?.find { it.name == itemDeath?.name }?.imageUrl = itemImage?.link
        val itemUserSaved = userRepository.save(itemUser!!)
        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun deleteImageToDeathInUser(username: String, name: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemDeath = deathRepository.findDeathByUsernameAndName(username, name)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.deaths?.find { it.name == itemDeath?.name }?.imageUrl = ""
        val itemUserSaved = userRepository.save(itemUser!!)
        imageRepository.delete(itemImage!!)

        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun updateServiceInUser(username: String, serviceId: UUID, serviceDTO: ServiceDTO): UserDTO? {
        var userItem = userRepository.findUserByUsername(username)
        val serviceFound = userItem?.services?.find { it.idService == serviceId }
        if (serviceFound != DataConverter.serviceFromDTO(serviceDTO)){
            serviceDTO.idService = serviceId
            serviceDTO.username = username

            userItem?.services?.set(userItem.services!!.indexOf(serviceFound!!), DataConverter.serviceFromDTO(serviceDTO))
            userItem = userRepository.save(userItem!!)
        }
        return DataConverter.userToDTO(userItem!!)
    }

    override fun addServiceInUser(username: String, serviceDTO: ServiceDTO): UserDTO? {
        var itemUser = userRepository.findUserByUsername(username)
            serviceDTO.idService = UUID.randomUUID()
            serviceDTO.username = itemUser?.username
            serviceDTO.category = serviceDTO.category
            itemUser?.services?.add(DataConverter.serviceFromDTO(serviceDTO))
            itemUser = userRepository.save(itemUser!!)
        return DataConverter.userToDTO(itemUser!!)
    }

    override fun deleteServiceInUser(username: String, idService: UUID): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        itemUser?.services?.removeIf { it.idService == idService }
        val itemSaved = userRepository.save(itemUser!!)
        serviceRepository.deleteById(idService)
        return DataConverter.userToDTO(itemSaved)
    }

    override fun addImageToServiceInUser(username: String, owner: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemService = serviceRepository.findServiceByUsernameAndOwner(username, owner)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.services?.find { it.owner == itemService?.owner }?.imageUrl = itemImage?.link
        val itemUserSaved = userRepository.save(itemUser!!)
        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun deleteImageToServiceInUser(username: String, owner: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemServices = serviceRepository.findServiceByUsernameAndOwner(username, owner)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.services?.find { it.owner == itemServices?.owner }?.imageUrl = ""
        val itemUserSaved = userRepository.save(itemUser!!)
        imageRepository.delete(itemImage!!)
        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun updateNewsInUser(username: String, newsId: UUID, newsDTO: NewsDTO): UserDTO? {
        var userItem = userRepository.findUserByUsername(username)
        val newsFound = userItem?.news?.find { it.idNew == newsId }
        if (newsFound != DataConverter.newsFromDTO(newsDTO)){
            newsDTO.idNew = newsId
            newsDTO.username = username

            userItem?.news?.set(userItem.news!!.indexOf(newsFound!!), DataConverter.newsFromDTO(newsDTO))
            userItem = userRepository.save(userItem!!)
        }
        return DataConverter.userToDTO(userItem!!)
    }

    override fun addNewsInUser(username: String, newsDTO: NewsDTO): UserDTO? {
        var itemUser = userRepository.findUserByUsername(username)
            newsDTO.idNew = UUID.randomUUID()
            newsDTO.username = itemUser?.username
            itemUser?.news?.add(DataConverter.newsFromDTO(newsDTO))
            itemUser = userRepository.save(itemUser!!)

        return DataConverter.userToDTO(itemUser)
    }

    override fun deleteNewsInUser(username: String, idNews: UUID): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        itemUser?.news?.removeIf { it.idNew == idNews }
        val itemSaved = userRepository.save(itemUser!!)
        newsRepository.deleteById(idNews)
        return DataConverter.userToDTO(itemSaved)
    }

    override fun addImageToNewsInUser(username: String, title: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemNew = newsRepository.findNewByUsernameAndTitle(username, title)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.news?.find { it.title == itemNew?.title }?.imageUrl = itemImage?.link
        val itemUserSaved = userRepository.save(itemUser!!)
        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun deleteImageToNewsInUser(username: String, title: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemNew = newsRepository.findNewByUsernameAndTitle(username, title)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.news?.find { it.title == itemNew?.title }?.imageUrl = ""
        val itemUserSaved = userRepository.save(itemUser!!)
        imageRepository.delete(itemImage!!)
        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun addIncidentInUser(username: String, incidentDTO: IncidentDTO): UserDTO? {
        var itemUser = userRepository.findUserByUsername(username)
            incidentDTO.idIncidence = UUID.randomUUID()
            incidentDTO.username = username
            itemUser?.incidents?.add(DataConverter.incidenceFromDTO(incidentDTO))
            itemUser = userRepository.save(itemUser!!)
        return DataConverter.userToDTO(itemUser)
    }

    override fun solveIncidence(username: String, incidentId: UUID, solution: String): UserDTO? {
        val userItem = userRepository.findUserByUsername(username)
        userItem?.incidents?.find { it.idIncidence == incidentId }.also {
            it?.isSolved = true
            it?.solution = solution
        }
        val userToSaved = userRepository.save(userItem!!)
        return DataConverter.userToDTO(userToSaved)
    }

    override fun updateBandoInUser(username: String, bandoId: UUID, bandoDTO: BandoDTO): UserDTO? {
        var userItem = userRepository.findUserByUsername(username)
        val bandoFound = userItem?.bandos?.find { it.idBando == bandoId }
        if (bandoFound != DataConverter.bandoFromDTO(bandoDTO)){
            bandoDTO.idBando = bandoId
            bandoDTO.username = username

            userItem?.bandos?.set(userItem.bandos!!.indexOf(bandoFound!!), DataConverter.bandoFromDTO(bandoDTO))
            userItem = userRepository.save(userItem!!)
        }
        return DataConverter.userToDTO(userItem!!)
    }

    override fun addBandoInUser(username: String, bandoDTO: BandoDTO): UserDTO? {
        var itemUser = userRepository.findUserByUsername(username)
                bandoDTO.idBando = UUID.randomUUID()
                bandoDTO.username = username
                itemUser?.bandos?.add(DataConverter.bandoFromDTO(bandoDTO))
                itemUser = userRepository.save(itemUser!!)

                if (fcmTokenRepository.findAll().any { it.username == username }){
                    val restTemplate = RestTemplate()
                    val map: Map<String, String> = mapOf(
                        "image" to "${bandoDTO.imageUrl}",
                        "subject" to "Publicación de Bando",
                        "content" to "Bando: ${bandoDTO.title}",
                        "username" to "${itemUser.username}")

                    val response: ResponseEntity<Void> = restTemplate.postForEntity(Urls.urlSendNotification, map, Void::class.java)
                }
        return DataConverter.userToDTO(itemUser)
    }

    override fun deleteBandoInUser(username: String, idBando: UUID): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        itemUser?.bandos?.removeIf { it.idBando == idBando }
        val itemSaved = userRepository.save(itemUser!!)
        bandoRepository.deleteById(idBando)
        return DataConverter.userToDTO(itemSaved)
    }

    override fun addImageToBandoInUser(username: String, title: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemBando = bandoRepository.findBandoByUsernameAndTitle(username, title)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.bandos?.find { it.title == itemBando?.title }?.imageUrl = itemImage?.link
        val itemUserSaved = userRepository.save(itemUser!!)
        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun deleteImageToBandoInUser(username: String, title: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemBando = bandoRepository.findBandoByUsernameAndTitle(username, title)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.bandos?.find { it.title == itemBando?.title }?.imageUrl = ""
        val itemUserSaved = userRepository.save(itemUser!!)
        imageRepository.delete(itemImage!!)
        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun updateLinkInUser(username: String, linkId: UUID, linkDTO: LinkDTO): UserDTO? {
        var userItem = userRepository.findUserByUsername(username)
        val linkFound = userItem?.links?.find { it.idLink == linkId }
        if (linkFound != DataConverter.linkFromDTO(linkDTO)){
            linkDTO.idLink = linkId
            linkDTO.username = username

            userItem?.links?.set(userItem.links!!.indexOf(linkFound!!), DataConverter.linkFromDTO(linkDTO))
            userItem = userRepository.save(userItem!!)
        }
        return DataConverter.userToDTO(userItem!!)
    }

    override fun addLinkInUser(username: String, linkDTO: LinkDTO): UserDTO? {
        var itemUser = userRepository.findUserByUsername(username)

        when(linkRepository.findLinkByUsernameAndTitle(username = username, title = linkDTO.title!!)){
            null -> {
                linkDTO.idLink = UUID.randomUUID()
                linkDTO.username = username
                itemUser?.links?.add(DataConverter.linkFromDTO(linkDTO))
                itemUser = userRepository.save(itemUser!!)
            }
            else -> {
                val checkIfExistLink = itemUser?.links?.find { it.title == linkDTO.title }
                if (checkIfExistLink == null){
                    linkDTO.idLink = UUID.randomUUID()
                    linkDTO.username = username
                    itemUser?.links?.add(DataConverter.linkFromDTO(linkDTO))
                    itemUser = userRepository.save(itemUser!!)
                }
            }
        }
        return DataConverter.userToDTO(itemUser!!)
    }
    override fun deleteLinkInUser(username: String, idLink: UUID): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemLink = linkRepository.findLinkByUsernameAndIdLink(username, idLink)
       itemUser?.links?.remove(itemLink)
        val itemSaved = userRepository.save(itemUser!!)
        linkRepository.delete(itemLink!!)
        return DataConverter.userToDTO(itemSaved)
    }

    override fun updateSponsorInUser(username: String, sponsorId: UUID, sponsorDTO: SponsorDTO): UserDTO? {
        var userItem = userRepository.findUserByUsername(username)
        val sponsorFound = userItem?.sponsors?.find { it.idSponsor == sponsorId }
        if (sponsorFound != DataConverter.sponsorFromDTO(sponsorDTO)){
            sponsorDTO.idSponsor = sponsorId
            sponsorDTO.username = username

            userItem?.sponsors?.set(userItem.sponsors!!.indexOf(sponsorFound!!), DataConverter.sponsorFromDTO(sponsorDTO))
            userItem = userRepository.save(userItem!!)
        }
        return DataConverter.userToDTO(userItem!!)
    }


    override fun addSponsorInUser(username: String, sponsorDTO: SponsorDTO): UserDTO? {
        var itemUser = userRepository.findUserByUsername(username)

        when(sponsorRepository.findSponsorByUsernameAndTitle(username, sponsorDTO.title!!)){
            null -> {
                sponsorDTO.idSponsor = UUID.randomUUID()
                sponsorDTO.username = username
                itemUser?.sponsors?.add(DataConverter.sponsorFromDTO(sponsorDTO))
                itemUser = userRepository.save(itemUser!!)
            }
            else -> {
                val checkIfExistSponsor = itemUser?.sponsors?.find { it.title == sponsorDTO.title }
                if (checkIfExistSponsor == null){
                    sponsorDTO.idSponsor = UUID.randomUUID()
                    sponsorDTO.username = username
                    itemUser?.sponsors?.add(DataConverter.sponsorFromDTO(sponsorDTO))
                    itemUser = userRepository.save(itemUser!!)
                }
            }
        }
        return DataConverter.userToDTO(itemUser!!)
    }

    override fun deleteSponsorInUser(username: String, idSponsor: UUID): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)

        itemUser?.sponsors?.removeIf { it.idSponsor == idSponsor }
        val itemSaved = userRepository.save(itemUser!!)
        sponsorRepository.deleteById(idSponsor)
        return DataConverter.userToDTO(itemSaved)
    }

    override fun addImageToSponsorInUser(username: String, title: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemSponsor = sponsorRepository.findSponsorByUsernameAndTitle(username, title)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.sponsors?.find { it.title == itemSponsor?.title }?.urlImage = itemImage?.link
        val itemUserSaved = userRepository.save(itemUser!!)
        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun deleteImageToSponsorInUser(username: String, title: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemSponsor = bandoRepository.findBandoByUsernameAndTitle(username, title)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.sponsors?.find { it.title == itemSponsor?.title }?.urlImage = ""
        val itemUserSaved = userRepository.save(itemUser!!)
        imageRepository.delete(itemImage!!)
        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun updateAdInUser(username: String, adId: UUID, adDTO: AdDTO): UserDTO? {
        var userItem = userRepository.findUserByUsername(username)
        val adFound = userItem?.ads?.find { it.idAd == adId }
        if (adFound != DataConverter.adFromDTO(adDTO)){
            adDTO.idAd = adId
            adDTO.username = username

            userItem?.ads?.set(userItem.ads!!.indexOf(adFound!!), DataConverter.adFromDTO(adDTO))
            userItem = userRepository.save(userItem!!)
        }
        return DataConverter.userToDTO(userItem!!)
    }

    override fun addAdInUser(username: String, adDTO: AdDTO): UserDTO? {
        var itemUser = userRepository.findUserByUsername(username)
                adDTO.idAd = UUID.randomUUID()
                adDTO.username = username
                itemUser?.ads?.add(DataConverter.adFromDTO(adDTO))
                itemUser = userRepository.save(itemUser!!)
        return DataConverter.userToDTO(itemUser)
    }

    override fun deleteAdInUser(username: String, idAd: UUID): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        itemUser?.ads?.removeIf { it.idAd == idAd }
        val itemSaved = userRepository.save(itemUser!!)
        adRepository.deleteById(idAd)
        return DataConverter.userToDTO(itemSaved)
    }

    override fun addReserveInUser(
        username: String,
        reserveDTO: ReserveDTO,
        idPlace: UUID,
        idHall: UUID
    ): UserDTO? {
        var userItem = userRepository.findUserByUsername(username)
        val hallItem = hallRepository.findHallByUsernameAndIdHall(username, idHall)
        val placeItem = placeRepository.findPlaceByUsernameAndIdPlace(username, idPlace)

        reserveDTO.idReserve = UUID.randomUUID()
        reserveDTO.username = username
        reserveDTO.place = DataConverter.placeToDTO(placeItem!!)
        reserveDTO.place!!.halls?.add(DataConverter.hallToDTO(hallItem!!))
        reserveDTO.hall = hallItem?.name
        reserveDTO.isReserved = false
        userItem?.reserves?.add(DataConverter.reserveFromDTO(reserveDTO))
        userItem = userRepository.save(userItem!!)

        return DataConverter.userToDTO(userItem)
    }

    override fun addReserveDataUser(username: String, reserveName: String, reserveUserDTO: ReserveUserDTO): UserDTO? {
        var userItem = userRepository.findUserByUsername(username)
        val reserveItem = reserveRepository.findReserveByUsernameAndName(username, reserveName)

        if (reserveItem?.reserveUsers!!.isEmpty()){
            reserveUserDTO.idReserveUser = UUID.randomUUID()
            userItem?.reserves?.find { it.name == reserveName }?.reserveUsers?.add(DataConverter.reserveUserFromDTO(reserveUserDTO))
           // reserveItem.reserveUsers?.add(DataConverter.reserveUserFromDTO(reserveUserDTO))
           userItem = userRepository.save(userItem!!)
        }
        return DataConverter.userToDTO(userItem!!)
    }

    override fun deleteReserveFromUser(username: String, idReserve: UUID): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemReserve = reserveRepository.findReserveByUsernameAndIdReserve(username, idReserve)
        itemUser?.reserves?.remove(itemReserve)
        val itemSaved = userRepository.save(itemUser!!)
        reserveRepository.delete(itemReserve!!)
        return DataConverter.userToDTO(itemSaved)
    }

    override fun confirmReserve(username: String, idReserve: UUID): UserDTO? {
        val userItem = userRepository.findUserByUsername(username)
        userItem?.reserves?.find { it.idReserve == idReserve }.also {
            it?.isReserved = true

            if (it?.reserveUsers!!.isNotEmpty()){
                it.reserveUsers!![0].also { reserveUser ->
                    reserveUser.isReserved = true
                }
            }
        }
        val userToSave = userRepository.save(userItem!!)
        return DataConverter.userToDTO(userToSave)
    }

    override fun addPlaceInUser(username: String, placeDTO: PlaceDTO): UserDTO? {
        var userItem = userRepository.findUserByUsername(username)
                    placeDTO.idPlace = UUID.randomUUID()
                    placeDTO.username = username
                    userItem?.places?.add(DataConverter.placeFromDTO(placeDTO))
                    userItem = userRepository.save(userItem!!)
        return DataConverter.userToDTO(userItem)
    }

    override fun deletePlaceFromUser(username: String, idReserve: UUID): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemPlace = placeRepository.findPlaceByUsernameAndIdPlace(username, idReserve)

        if(itemPlace?.imageUrl != null){
            val itemImageDelete = imageRepository.findImageByLink(itemPlace.imageUrl!!)
            imageRepository.delete(itemImageDelete!!)
        }

        itemUser?.places?.remove(itemPlace)
        val itemSaved = userRepository.save(itemUser!!)
        placeRepository.delete(itemPlace!!)
        return DataConverter.userToDTO(itemSaved)
    }

    override fun updatePlaceInUser(username: String, placeId: UUID, placeDTO: PlaceDTO): UserDTO? {
        var userItem = userRepository.findUserByUsername(username)
        val placeFound = userItem?.places?.find { it.idPlace == placeId }
        if (placeFound != DataConverter.placeFromDTO(placeDTO)){
            placeDTO.idPlace = placeId
            placeDTO.username = username

            userItem?.places?.set(userItem.places!!.indexOf(placeFound!!), DataConverter.placeFromDTO(placeDTO))
            userItem = userRepository.save(userItem!!)
        }
        return DataConverter.userToDTO(userItem!!)
    }

    override fun getSectionDetails(username: String): SectionDetailsDTO? {
        val userItem = userRepository.findUserByUsername(username)
        val gallery = imageRepository.findImagesByLocality(username)
        return SectionDetailsDTO(
            eventQuantity = userItem?.events?.size,
            tourismQuantity = userItem?.tourism?.size,
            pharmacyQuantity = userItem?.pharmacies?.size,
            serviceQuantity = userItem?.services?.size,
            newsQuantity = userItem?.news?.size,
            bandoQuantity = userItem?.bandos?.size,
            adQuantity = userItem?.ads?.size,
            galleryQuantity = gallery?.size,
            deathQuantity = userItem?.deaths?.size,
            linkQuantity = userItem?.links?.size,
            sponsorQuantity = userItem?.sponsors?.size,
            incidentQuantity = userItem?.incidents?.size,
            reserveQuantity = userItem?.reserves?.size
        )
    }

    override fun addLinkCustomInUser(username: String, customLinkDTO: CustomLinkDTO): UserDTO? {
        val userItem = userRepository.findUserByUsername(username)
        customLinkDTO.idCustomLink = UUID.randomUUID()
        customLinkDTO.username = username
        userItem?.customLinks?.add(DataConverter.customLinkFromDTO(customLinkDTO))
        val userToSave = userRepository.save(userItem!!)
        return DataConverter.userToDTO(userToSave)
    }

    override fun updateLinkCustomInUser(username: String, idCustomLink: UUID, customLinkDTO: CustomLinkDTO): UserDTO? {
        var userItem = userRepository.findUserByUsername(username)
        val customLinkFound = userItem?.customLinks?.find {it.idCustomLink == idCustomLink  }
        if (customLinkFound != DataConverter.customLinkFromDTO(customLinkDTO)) {
            customLinkDTO.idCustomLink = idCustomLink
            customLinkDTO.username = username
            userItem?.customLinks?.set(userItem.customLinks!!.indexOf(customLinkFound!!), DataConverter.customLinkFromDTO(customLinkDTO))
            userItem = userRepository.save(userItem!!)
        }
        return DataConverter.userToDTO(userItem!!)
    }

    override fun deleteLinkCustomInUser(username: String, idCustomLink: UUID): UserDTO? {
        val userItem = userRepository.findUserByUsername(username)
        userItem?.customLinks?.removeIf { it.idCustomLink == idCustomLink }
        val userSaved = userRepository.save(userItem!!)
        customLinkRepository.deleteById(idCustomLink)
        return DataConverter.userToDTO(userSaved)
    }

    override fun addQuizInUser(username: String, quizDTO: QuizDTO): UserDTO? {
        val userItem = userRepository.findUserByUsername(username)
        quizDTO.idQuiz = UUID.randomUUID()
        quizDTO.username = username
        quizDTO.resultOne = 0
        quizDTO.resultTwo = 0
        quizDTO.resultThree = 0
        quizDTO.resultFour = 0
        userItem?.quizzes?.add(DataConverter.quizFromDTO(quizDTO))
        val userToSave = userRepository.save(userItem!!)
        return DataConverter.userToDTO(userToSave)
    }

    override fun updateQuizInUser(username: String, idQuiz: UUID, quizDTO: QuizDTO): UserDTO? {
        var userItem = userRepository.findUserByUsername(username)
        val quizFound = userItem?.quizzes?.find {it.idQuiz == idQuiz  }
        if (quizFound != DataConverter.quizFromDTO(quizDTO)) {
            quizDTO.idQuiz = idQuiz
            quizDTO.username = username
            userItem?.quizzes?.set(userItem.quizzes!!.indexOf(quizFound!!), DataConverter.quizFromDTO(quizDTO))
            userItem = userRepository.save(userItem!!)
        }
        return DataConverter.userToDTO(userItem!!)
    }

    override fun updateResultQuizInUser(username: String, idQuiz: UUID, option: Int): UserDTO? {
        val userItem = userRepository.findUserByUsername(username)
         userItem?.quizzes?.find { it.idQuiz == idQuiz }.also { quiz ->
             when(option){
                 1 ->  quiz!!.resultOne = (quiz.resultOne!! + 1)
                 2 ->  quiz!!.resultTwo = (quiz.resultTwo!! + 1)
                 3 ->  quiz!!.resultThree = (quiz.resultThree!! + 1)
                 4 ->  quiz!!.resultFour = (quiz.resultFour!! + 1)
             }
         }
        val userSaved = userRepository.save(userItem!!)
        return DataConverter.userToDTO(userSaved)
    }

    override fun deleteQuizFromUser(username: String, idQuiz: UUID): UserDTO? {
        val userItem = userRepository.findUserByUsername(username)
        userItem?.quizzes?.removeIf { it.idQuiz == idQuiz }
        val userSaved = userRepository.save(userItem!!)
        quizRepository.deleteById(idQuiz)
        return DataConverter.userToDTO(userSaved)
    }
}