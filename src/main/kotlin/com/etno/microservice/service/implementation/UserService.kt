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
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val fcmTokenRepository: FCMTokenRepository,
    private val eventRepository: EventRepository,
    private val imageRepository: ImageRepository,
    private val pharmacyRepository: PharmacyRepository,
    private val tourismRepository: TourismRepository,
    private val deathRepository: DeathRepository,
    private val phoneRepository: PhoneRepository,
    private val newRepository: NewRepository,
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
                responseMap["user"] = DataConverter.userToDTO(user!!)

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
        val itemEvent = eventRepository.findEventByTitleAndUsername(eventDTO.title!!, username)

        if(itemEvent == null){
            eventDTO.username = itemUser?.username
            val itemEventSaved = eventRepository.save(DataConverter.eventFromDTO(eventDTO))
            itemUser?.events?.add(DataConverter.eventFromDTO(DataConverter.eventToDTO(itemEventSaved)))
            userRepository.save(itemUser!!)

            if (fcmTokenRepository.findAll().any { it.username == username }){
                val restTemplate = RestTemplate()
                val map: Map<String, String> = mapOf("subject" to "Nuevo evento", "content" to "Evento ${itemEventSaved.title} disponible", "username" to "${itemUser.username}")

                val response: ResponseEntity<Void> = restTemplate.postForEntity(Urls.urlSendNotification, map, Void::class.java)
            }

        }else{
            val checkIfExistEvent = itemUser?.events?.find { it.title == itemEvent.title }
            if(checkIfExistEvent == null){
                eventDTO.username = itemUser?.username
                itemUser?.events?.add(DataConverter.eventFromDTO(eventDTO))
                userRepository.save(itemUser!!)

                if (fcmTokenRepository.findAll().any { it.username == username }){
                    val restTemplate = RestTemplate()
                    val map: Map<String, String> = mapOf(
                        "subject" to "Nuevo evento",
                        "content" to "Evento ${eventDTO.title} disponible",
                        "username" to "${itemUser.username}")

                    val response: ResponseEntity<Void> = restTemplate.postForEntity(Urls.urlSendNotification, map, Void::class.java)
                }
            }
        }
        return DataConverter.userToDTO(itemUser)
    }
    override fun deleteEventInUser(username: String, title: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemEvent = eventRepository.findEventByTitleAndUsername(title, username)

        itemUser?.events?.remove(itemEvent!!)

        itemEvent?.images?.forEach {
           val itemImage = imageRepository.findImageByLink(it.link!!)
           imageRepository.delete(itemImage!!)
        }

        val itemSaved = userRepository.save(itemUser!!)
        eventRepository.delete(itemEvent!!)
        return DataConverter.userToDTO(itemSaved)
    }

    override fun addImageToEventInUser(username: String, title: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemEvent = eventRepository.findEventByTitleAndUsername(title, username)
        val itemImage = imageRepository.findImageByName(imageName)

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

    override fun addPharmacyInUser(username: String, pharmacyDTO: PharmacyDTO): UserDTO? {

        var itemUser = userRepository.findUserByUsername(username)
        val itemPharmacy = pharmacyRepository.findPharmacyByNameAndUsername(pharmacyDTO.name!!, username)

        if(itemPharmacy == null){
            pharmacyDTO.idPharmacy = UUID.randomUUID()
            pharmacyDTO.username = itemUser?.username
            itemUser?.pharmacies?.add(DataConverter.pharmacyFromDTO(pharmacyDTO))
            itemUser = userRepository.save(itemUser!!)
        }else{
            val checkIfExistPharmacy = itemUser?.pharmacies?.find { it.name == itemPharmacy.name }
            if(checkIfExistPharmacy == null){
                pharmacyDTO.idPharmacy = UUID.randomUUID()
                pharmacyDTO.username = itemUser?.username
                itemUser?.pharmacies?.add(DataConverter.pharmacyFromDTO(pharmacyDTO))
               itemUser = userRepository.save(itemUser!!)
            }
        }
        return DataConverter.userToDTO(itemUser!!)
    }

    override fun deletePharmacyInUser(username: String, name: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemPharmacy = pharmacyRepository.findPharmacyByNameAndUsername(name, username)
        val itemImageDelete = imageRepository.findImageByLink(itemPharmacy?.imageUrl!!)

        itemUser?.pharmacies?.remove(itemPharmacy)
        imageRepository.delete(itemImageDelete!!)
        val itemSaved = userRepository.save(itemUser!!)
        pharmacyRepository.delete(itemPharmacy)
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

    override fun addTourismInUser(username: String, tourismDTO: TourismDTO): UserDTO? {
        var itemUser = userRepository.findUserByUsername(username)
        val itemTourism = tourismRepository.findTourismByTitleAndUsername(tourismDTO.title!!, username)

        if(itemTourism == null){
            tourismDTO.idTourism = UUID.randomUUID()
            tourismDTO.username = username
            itemUser?.tourism?.add(DataConverter.tourismFromDTO(tourismDTO))
            itemUser = userRepository.save(itemUser!!)
        }else{
            val checkIfExistTourism = itemUser?.tourism?.find { it.title == itemTourism.title }
            if(checkIfExistTourism == null){
                tourismDTO.idTourism = UUID.randomUUID()
                tourismDTO.username = username
                itemUser?.tourism?.add(DataConverter.tourismFromDTO(tourismDTO))
              itemUser = userRepository.save(itemUser!!)
            }
        }
        return DataConverter.userToDTO(itemUser!!)
    }

    override fun deleteTourismInUser(username: String, title: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemTourism = tourismRepository.findTourismByTitleAndUsername(title, username)
        val itemImageDelete = imageRepository.findImageByLink(itemTourism?.imageUrl!!)

        itemUser?.tourism?.remove(itemTourism)
        imageRepository.delete(itemImageDelete!!)
        val itemUserSaved = userRepository.save(itemUser!!)
        tourismRepository.delete(itemTourism)
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

    override fun deleteDeathInUser(username: String, name: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemDeath = deathRepository.findDeathByUsernameAndName(username, name)
        val itemImageDelete = imageRepository.findImageByLink(itemDeath?.imageUrl!!)

        itemUser?.deaths?.remove(itemDeath)
        imageRepository.delete(itemImageDelete!!)
        val itemUserSaved = userRepository.save(itemUser!!)
        deathRepository.delete(itemDeath)
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

    override fun addPhoneInUser(username: String, phoneDTO: PhoneDTO): UserDTO? {
        var itemUser = userRepository.findUserByUsername(username)
        val itemPhone = phoneRepository.findPhoneByUsernameAndOwner(username, phoneDTO.owner!!)

        if(itemPhone == null){
            phoneDTO.idPhone = UUID.randomUUID()
            phoneDTO.username = itemUser?.username
            phoneDTO.category = phoneDTO.category
            itemUser?.phones?.add(DataConverter.phoneFromDTO(phoneDTO))
            itemUser = userRepository.save(itemUser!!)
        }else{
            val checkIfExistPhone = itemUser?.phones?.find { it.owner == itemPhone.owner }
            if(checkIfExistPhone == null){
                phoneDTO.idPhone = UUID.randomUUID()
                phoneDTO.username = itemUser?.username
                itemUser?.phones?.add(DataConverter.phoneFromDTO(phoneDTO))
                itemUser = userRepository.save(itemUser!!)
            }
        }
        return DataConverter.userToDTO(itemUser!!)
    }

    override fun deletePhoneInUser(username: String, owner: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemPhone = phoneRepository.findPhoneByUsernameAndOwner(username, owner)
        val itemImageDelete = imageRepository.findImageByLink(itemPhone?.imageUrl!!)

        itemUser?.phones?.remove(itemPhone)
        imageRepository.delete(itemImageDelete!!)
        val itemSaved = userRepository.save(itemUser!!)
        phoneRepository.delete(itemPhone)
        return DataConverter.userToDTO(itemSaved)
    }

    override fun addImageToPhoneInUser(username: String, owner: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemPhone = phoneRepository.findPhoneByUsernameAndOwner(username, owner)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.phones?.find { it.owner == itemPhone?.owner }?.imageUrl = itemImage?.link
        val itemUserSaved = userRepository.save(itemUser!!)
        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun deleteImageToPhoneInUser(username: String, owner: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemPhone = phoneRepository.findPhoneByUsernameAndOwner(username, owner)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.phones?.find { it.owner == itemPhone?.owner }?.imageUrl = ""
        val itemUserSaved = userRepository.save(itemUser!!)
        imageRepository.delete(itemImage!!)
        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun addNewInUser(username: String, newDTO: NewDTO): UserDTO? {
        var itemUser = userRepository.findUserByUsername(username)
        val itemNew = newRepository.findNewByUsernameAndTitle(username, newDTO.title!!)

        if(itemNew == null){
            newDTO.idNew = UUID.randomUUID()
            newDTO.username = itemUser?.username
            itemUser?.news?.add(DataConverter.newFromDTO(newDTO))
            itemUser = userRepository.save(itemUser!!)
        }else{
            val checkIfExistPhone = itemUser?.news?.find { it.title == itemNew.title }
            if(checkIfExistPhone == null){
                newDTO.idNew = UUID.randomUUID()
                newDTO.username = itemUser?.username
                itemUser?.news?.add(DataConverter.newFromDTO(newDTO))
                itemUser = userRepository.save(itemUser!!)
            }
        }
        return DataConverter.userToDTO(itemUser!!)
    }

    override fun deleteNewInUser(username: String, title: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemNew = newRepository.findNewByUsernameAndTitle(username, title)
        val itemImageDelete = imageRepository.findImageByLink(itemNew?.imageUrl!!)

        itemUser?.news?.remove(itemNew)
        imageRepository.delete(itemImageDelete!!)
        val itemSaved = userRepository.save(itemUser!!)
        newRepository.delete(itemNew)
        return DataConverter.userToDTO(itemSaved)
    }

    override fun addImageToNewInUser(username: String, title: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemNew = newRepository.findNewByUsernameAndTitle(username, title)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.news?.find { it.title == itemNew?.title }?.imageUrl = itemImage?.link
        val itemUserSaved = userRepository.save(itemUser!!)
        return DataConverter.userToDTO(itemUserSaved)
    }

    override fun deleteImageToNewInUser(username: String, title: String, imageName: String): UserDTO? {
        val itemUser = userRepository.findUserByUsername(username)
        val itemNew = newRepository.findNewByUsernameAndTitle(username, title)
        val itemImage = imageRepository.findImageByName(imageName)

        itemUser?.news?.find { it.title == itemNew?.title }?.imageUrl = ""
        val itemUserSaved = userRepository.save(itemUser!!)
        imageRepository.delete(itemImage!!)
        return DataConverter.userToDTO(itemUserSaved)
    }
}