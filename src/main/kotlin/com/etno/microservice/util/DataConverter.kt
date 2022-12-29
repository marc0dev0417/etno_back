package com.etno.microservice.util

import com.etno.microservice.model.*
import com.etno.microservice.model.dto.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class DataConverter {
    companion object {
        fun userToDTO(user: User): UserDTO {
            return UserDTO(
                idUser = user.idUser,
                username = user.username,
                password = user.password,
                events = user.events.let { it!!.map { event -> eventToDTO(event) } }.toMutableList(),
                pharmacies = user.pharmacies.let { it?.map { pharmacy -> pharmacyToDTO(pharmacy) } }?.toMutableList(),
                tourism = user.tourism.let { it?.map { tourism -> tourismToDTO(tourism) } }?.toMutableList(),
                deaths = user.deaths.let { it?.map { death -> deathToDTO(death) } }?.toMutableList(),
                phones = user.phones.let { it?.map { phone -> phoneToDTO(phone) } }?.toMutableList()
            )
        }

        fun userFromDTO(userDTO: UserDTO): User {
            return User(
                idUser = userDTO.idUser!!,
                username = userDTO.username,
                password = userDTO.password,
                events = userDTO.events.let { it?.map { eventDTO -> eventFromDTO(eventDTO) } }!!.toMutableList(),
                pharmacies = userDTO.pharmacies.let { it?.map { pharmacyDTO -> pharmacyFromDTO(pharmacyDTO) } }?.toMutableList(),
                tourism = userDTO.tourism.let { it?.map { tourismDTO -> tourismFromDTO(tourismDTO) } }?.toMutableList(),
                deaths = userDTO.deaths.let { it?.map { deathDTO -> deathFromDTO(deathDTO) } }?.toMutableList(),
                phones = userDTO.phones.let { it?.map { phoneDTO -> phoneFromDTO(phoneDTO) } }?.toMutableList()
            )
        }

        fun userVillagerToDTO(user: User): UserVillagerDTO {
            return UserVillagerDTO(
                username = user.username,
                events = user.events.let { it?.map { event -> eventToDTO(event) } }?.toMutableList(),
                pharmacies = user.pharmacies.let { it?.map { pharmacy -> pharmacyToDTO(pharmacy) } }?.toMutableList(),
                tourism = user.tourism.let { it?.map { tourism -> tourismToDTO(tourism) } }?.toMutableList(),
                deaths = user.deaths.let { it?.map { death -> deathToDTO(death) } }?.toMutableList(),
                phones = user.phones.let { it?.map { phone -> phoneToDTO(phone) } }?.toMutableList()
            )
        }

        fun eventToDTO(event: Event): EventDTO {
            val localTimeToFormat = LocalDateTime.ofInstant(event.publicationDate!!.toInstant(), ZoneId.systemDefault())
            val formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM")

            return EventDTO(
                idEvent = event.idEvent,
                username = event.username,
                title = event.title,
                address = event.address,
                description = event.description,
                organization = event.organization,
                reservePrice = event.reservePrice,
                link = event.link,
                startDate = event.startDate,
                endDate = event.endDate,
                publicationDate = localTimeToFormat.format(formatter),
                time = event.time,
                lat = event.lat,
                long = event.long,
                images = event.images.let { event.images?.map { image -> imageToDTO(image) } }?.toMutableList(),
                videos = event.videos.let { event.videos?.map { video -> videoToDTO(video) } }?.toMutableList()
            )
        }

        fun eventFromDTO(eventDTO: EventDTO): Event {
            return Event(
                idEvent = eventDTO.idEvent!!,
                username = eventDTO.username,
                title = eventDTO.title,
                address = eventDTO.address,
                description = eventDTO.description,
                organization = eventDTO.organization,
                reservePrice = eventDTO.reservePrice,
                link = eventDTO.link,
                startDate = eventDTO.startDate,
                endDate = eventDTO.endDate,
                publicationDate = Date(),
                time = eventDTO.time,
                lat = eventDTO.lat,
                long = eventDTO.long,
                images = eventDTO.images.let { eventDTO.images?.map { imageDTO -> imageFromDTO(imageDTO) } }?.toMutableList(),
                videos = eventDTO.videos.let { eventDTO.videos?.map { videoDTO -> videoFromDTO(videoDTO) } }?.toMutableList()
            )
        }

        fun imageToDTO(image: Image): ImageDTO{
            return ImageDTO(
                idImage = image.idImage,
                name = image.name,
                link = image.link
            )
        }
        fun imageFromDTO(imageDTO: ImageDTO): Image{
            return Image(
                idImage = imageDTO.idImage!!,
                name = imageDTO.name,
                link = imageDTO.link
            )
        }

        fun videoToDTO(video: Video): VideoDTO{
            return VideoDTO(
                idVideo = video.idVideo!!,
                link = video.link
            )
        }
        fun videoFromDTO(videoDTO: VideoDTO): Video{
            return Video(
                idVideo = videoDTO.idVideo,
                link = videoDTO.link
            )
        }

        fun tourismToDTO(tourism: Tourism): TourismDTO{
            return TourismDTO(
                idTourism = tourism.idTourism,
                type = tourism.type,
                username = tourism.username,
                title = tourism.title,
                description = tourism.description,
                imageUrl = tourism.imageUrl,
                latitude = tourism.latitude,
                longitude = tourism.longitude
            )
        }
        fun tourismFromDTO(tourismDTO: TourismDTO): Tourism{
            return Tourism(
                idTourism = tourismDTO.idTourism,
                type = tourismDTO.type,
                username = tourismDTO.username,
                title = tourismDTO.title,
                description = tourismDTO.description,
                imageUrl = tourismDTO.imageUrl,
                latitude = tourismDTO.latitude,
                longitude = tourismDTO.longitude,
            )
        }

        fun festivityToDTO(festivity: Festivity): FestivityDTO{
            return FestivityDTO(
                idFestivity = festivity.idFestivity,
                title = festivity.title,
                address = festivity.address,
                description = festivity.description,
                organization = festivity.organization,
                link = festivity.link,
                startDate = festivity.startDate,
                endDate = festivity.endDate,
                publicationDate = festivity.publicationDate,
                time = festivity.time,
                lat = festivity.lat,
                long = festivity.long,
                images = festivity.images.let { festivity.images?.map { image -> imageToDTO(image) } }?.toMutableList(),
                videos = festivity.videos.let { festivity.videos?.map { video -> videoToDTO(video) } }?.toMutableList()
            )
        }
        fun festivityFromDTO(festivityDTO: FestivityDTO): Festivity{
            return Festivity(
                idFestivity = festivityDTO.idFestivity,
                title = festivityDTO.title,
                address = festivityDTO.address,
                description = festivityDTO.description,
                organization = festivityDTO.organization,
                link = festivityDTO.link,
                startDate = festivityDTO.startDate,
                endDate = festivityDTO.endDate,
                publicationDate = festivityDTO.publicationDate,
                time = festivityDTO.time,
                lat = festivityDTO.lat,
                long = festivityDTO.long,
                images = festivityDTO.images.let { festivityDTO.images?.map { imageDTO -> imageFromDTO(imageDTO) } }?.toMutableList(),
                videos = festivityDTO.videos.let { festivityDTO.videos?.map { videoDTO -> videoFromDTO(videoDTO) } }?.toMutableList()
            )
        }

        fun fcmTokenToDTO(fcmToken: FCMToken): FCMTokenDTO{
            return FCMTokenDTO(
                idFMC = fcmToken.idFMC,
                username = fcmToken.username,
                token = fcmToken.token
            )
        }
        fun fcmTokenFromDTO(fcmTokenDTO: FCMTokenDTO): FCMToken{
            return FCMToken(
                idFMC = fcmTokenDTO.idFMC,
                username = fcmTokenDTO.username,
                token = fcmTokenDTO.token
            )
        }

        fun subscriptionToDTO(subscription: Subscription): SubscriptionDTO{
            return SubscriptionDTO(
                idSubscription = subscription.idSectionSubscribe,
                category = subscription.category,
                title = subscription.title,
                isSubscribe = subscription.isSubscribe,
                subscriptionUsers = subscription.subscriptionsUsers.let { subscription.subscriptionsUsers?.map { subscriptionUser -> subscriptionUserToDTO(subscriptionUser) } }!!.toMutableList()
            )
        }

        fun subscriptionFromDTO(subscriptionDTO: SubscriptionDTO): Subscription{
            return Subscription(
                idSectionSubscribe = subscriptionDTO.idSubscription!!,
                category = subscriptionDTO.category,
                title = subscriptionDTO.title,
                isSubscribe = subscriptionDTO.isSubscribe,
                subscriptionsUsers = subscriptionDTO.subscriptionUsers.let { subscriptionDTO.subscriptionUsers.map { subscriptionUserDTO -> subscriptionUserFromDTO(subscriptionUserDTO) } }.toMutableList()
            )
        }

        fun subscriptionUserToDTO(subscriptionUser: SubscriptionUser): SubscriptionUserDTO{
            return SubscriptionUserDTO(
                idSubscriptionUser = subscriptionUser.idSubscriptionUser,
                fcmToken = subscriptionUser.fcmToken,
                name = subscriptionUser.name,
                mail = subscriptionUser.mail,
                phone = subscriptionUser.phone,
                wallet = subscriptionUser.wallet,
                isSubscribe = subscriptionUser.isSubscribe
            )
        }
        fun subscriptionUserFromDTO(subscriptionUserDTO: SubscriptionUserDTO): SubscriptionUser{
            return SubscriptionUser(
                idSubscriptionUser = subscriptionUserDTO.idSubscriptionUser,
                fcmToken = subscriptionUserDTO.fcmToken,
                name = subscriptionUserDTO.name,
                mail = subscriptionUserDTO.mail,
                phone = subscriptionUserDTO.phone,
                wallet = subscriptionUserDTO.wallet,
                isSubscribe = subscriptionUserDTO.isSubscribe
            )
        }

        fun pharmacyToDTO(pharmacy: Pharmacy): PharmacyDTO{
            return PharmacyDTO(
                idPharmacy = pharmacy.idPharmacy,
                username = pharmacy.username,
                type = pharmacy.type,
                name = pharmacy.name,
                link = pharmacy.link,
                imageUrl = pharmacy.imageUrl,
                phone = pharmacy.phone,
                schedule = pharmacy.schedule,
                description = pharmacy.description,
                longitude = pharmacy.longitude,
                latitude = pharmacy.latitude
            )
        }
        fun pharmacyFromDTO(pharmacyDTO: PharmacyDTO): Pharmacy{
            return Pharmacy(
                idPharmacy = pharmacyDTO.idPharmacy!!,
                username = pharmacyDTO.username,
                type = pharmacyDTO.type,
                link = pharmacyDTO.link,
                imageUrl = pharmacyDTO.imageUrl,
                name = pharmacyDTO.name,
                phone = pharmacyDTO.phone,
                schedule = pharmacyDTO.schedule,
                description = pharmacyDTO.description,
                longitude = pharmacyDTO.longitude,
                latitude = pharmacyDTO.latitude
            )
        }

        fun deathToDTO(death: Death): DeathDTO{
            return DeathDTO(
                idDeath = death.idDeath,
                username = death.username,
                name = death.name,
                deathDate = death.deathDate,
                description = death.description,
                imageUrl = death.imageUrl
            )
        }
        fun deathFromDTO(deathDTO: DeathDTO): Death{
            return Death(
                idDeath = deathDTO.idDeath,
                username = deathDTO.username,
                name = deathDTO.name,
                deathDate = deathDTO.deathDate,
                description = deathDTO.description,
                imageUrl = deathDTO.imageUrl
            )
        }

        fun phoneToDTO(phone: Phone): PhoneDTO{
            return PhoneDTO(
                idPhone = phone.idPhone,
                username = phone.username,
                category = phone.category,
                owner = phone.owner,
                number = phone.number,
                imageUrl = phone.imageUrl
            )
        }
        fun phoneFromDTO(phoneDTO: PhoneDTO): Phone{
            return Phone(
                idPhone = phoneDTO.idPhone,
                username = phoneDTO.username,
                category = phoneDTO.category,
                owner = phoneDTO.owner,
                number = phoneDTO.number,
                imageUrl = phoneDTO.imageUrl
            )
        }
    }
}