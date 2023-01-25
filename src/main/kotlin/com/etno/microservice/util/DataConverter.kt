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
                phones = user.phones.let { it?.map { phone -> phoneToDTO(phone) } }?.toMutableList(),
                news = user.news.let { it?.map { new -> newToDTO(new) } }?.toMutableList(),
                incidents = user.incidents.let { it?.map { incident -> incidenceToDTO(incident) } }?.toMutableList(),
                bandos = user.bandos.let { it?.map { bando: Bando -> bandoToDTO(bando) } }?.toMutableList(),
                links = user.links.let { it?.map { link -> linkToDTO(link) } }?.toMutableList(),
                sponsors = user.sponsors.let { it?.map { sponsor -> sponsorToDTO(sponsor) } }?.toMutableList(),
                ads = user.ads.let { it?.map { ad -> adToDTO(ad) } }?.toMutableList()
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
                phones = userDTO.phones.let { it?.map { phoneDTO -> phoneFromDTO(phoneDTO) } }?.toMutableList(),
                news = userDTO.news.let { it?.map { newDTO -> newFromDTO(newDTO) } }?.toMutableList(),
                incidents = userDTO.incidents.let { it?.map { incidentDTO -> incidenceFromDTO(incidentDTO) } }?.toMutableList(),
                bandos = userDTO.bandos.let { it?.map { bandoDTO -> bandoFromDTO(bandoDTO) } }?.toMutableList(),
                links = userDTO.links.let { it?.map { linkDTO -> linkFromDTO(linkDTO) } }?.toMutableList(),
                sponsors = userDTO.sponsors.let { it?.map { sponsorDTO -> sponsorFromDTO(sponsorDTO) } }?.toMutableList(),
                ads = userDTO.ads.let { it?.map { adDTO -> adFromDTO(adDTO) } }?.toMutableList()
            )
        }

        fun userVillagerToDTO(user: User): UserVillagerDTO {
            return UserVillagerDTO(
                username = user.username,
                events = user.events.let { it?.map { event -> eventToDTO(event) } }?.toMutableList(),
                pharmacies = user.pharmacies.let { it?.map { pharmacy -> pharmacyToDTO(pharmacy) } }?.toMutableList(),
                tourism = user.tourism.let { it?.map { tourism -> tourismToDTO(tourism) } }?.toMutableList(),
                deaths = user.deaths.let { it?.map { death -> deathToDTO(death) } }?.toMutableList(),
                phones = user.phones.let { it?.map { phone -> phoneToDTO(phone) } }?.toMutableList(),
                news = user.news.let { it?.map { new -> newToDTO(new) } }?.toMutableList(),
                ads = user.ads.let { it?.map { ad -> adToDTO(ad) } }?.toMutableList()
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
                seats = event.seats,
                capacity = event.capacity,
                link = event.link,
                imageUrl = event.imageUrl,
                startDate = event.startDate,
                endDate = event.endDate,
                publicationDate = localTimeToFormat.format(formatter),
                time = event.time,
                lat = event.lat,
                long = event.long,
                images = event.images.let { event.images?.map { image -> imageToDTO(image) } }?.toMutableList(),
                videos = event.videos.let { event.videos?.map { video -> videoToDTO(video) } }?.toMutableList(),
                userSubscriptions = event.userSubscriptions.let { event.userSubscriptions?.map { subscriptionUser -> subscriptionUserToDTO(subscriptionUser) } }?.toMutableList()
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
                seats = eventDTO.seats,
                capacity = eventDTO.capacity,
                link = eventDTO.link,
                imageUrl = eventDTO.imageUrl,
                startDate = eventDTO.startDate,
                endDate = eventDTO.endDate,
                publicationDate = Date(),
                time = eventDTO.time,
                lat = eventDTO.lat,
                long = eventDTO.long,
                images = eventDTO.images.let { eventDTO.images?.map { imageDTO -> imageFromDTO(imageDTO) } }?.toMutableList(),
                videos = eventDTO.videos.let { eventDTO.videos?.map { videoDTO -> videoFromDTO(videoDTO) } }?.toMutableList(),
                userSubscriptions = eventDTO.userSubscriptions.let { eventDTO.userSubscriptions?.map { subscriptionUserDTO -> subscriptionUserFromDTO(subscriptionUserDTO) }?.toMutableList()  }
            )
        }

        fun imageToDTO(image: Image): ImageDTO{
            return ImageDTO(
                idImage = image.idImage,
                locality = image.locality,
                section = image.section,
                name = image.name,
                category = image.category,
                link = image.link
            )
        }
        fun imageFromDTO(imageDTO: ImageDTO): Image{
            return Image(
                idImage = imageDTO.idImage!!,
                locality = imageDTO.locality,
                section = imageDTO.section,
                name = imageDTO.name,
                category = imageDTO.category,
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
        fun subscriptionUserToDTO(subscriptionUser: SubscriptionUser): SubscriptionUserDTO{
            return SubscriptionUserDTO(
                idSubscriptionUser = subscriptionUser.idSubscriptionUser,
                fcmToken = subscriptionUser.fcmToken,
                title = subscriptionUser.title,
                seats = subscriptionUser.seats,
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
                title = subscriptionUserDTO.title,
                seats = subscriptionUserDTO.seats,
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
                schedule = phone.schedule,
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
                schedule = phoneDTO.schedule,
                imageUrl = phoneDTO.imageUrl
            )
        }

        fun newToDTO(new: New): NewDTO {
            return NewDTO(
                idNew = new.idNew,
                username = new.username,
                category = new.category,
                title = new.title,
                publicationDate = new.publicationDate,
                description = new.description,
                imageUrl = new.imageUrl
            )
        }
        fun newFromDTO(newDTO: NewDTO): New{
            return New(
                idNew = newDTO.idNew,
                username = newDTO.username,
                category = newDTO.category,
                title = newDTO.title,
                publicationDate = newDTO.publicationDate,
                description = newDTO.description,
                imageUrl = newDTO.imageUrl
            )
        }
        fun incidenceToDTO(incidence: Incident): IncidentDTO{
            return IncidentDTO(
                idIncidence = incidence.idIncidence,
                username = incidence.username,
                fcmToken = incidence.fcmToken,
                title = incidence.title,
                description = incidence.description
            )
        }
        fun incidenceFromDTO(incidentDTO: IncidentDTO): Incident{
            return Incident(
                idIncidence = incidentDTO.idIncidence,
                username = incidentDTO.username,
                fcmToken = incidentDTO.fcmToken,
                title = incidentDTO.title,
                description = incidentDTO.description
            )
        }

        fun bandoToDTO(bando: Bando): BandoDTO{
            val localTimeToFormat = LocalDateTime.ofInstant(bando.issuedDate?.toInstant(), ZoneId.systemDefault())
            val formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM")
            return BandoDTO(
                idBando =  bando.idBando,
                username = bando.username,
                title = bando.title,
                description = bando.description,
                issuedDate = localTimeToFormat.format(formatter),
                imageUrl = bando.imageUrl
            )
        }
        fun bandoFromDTO(bandoDTO: BandoDTO): Bando{
            return Bando(
                idBando = bandoDTO.idBando,
                username = bandoDTO.username,
                title = bandoDTO.title,
                description = bandoDTO.description,
                issuedDate = Date(),
                imageUrl = bandoDTO.imageUrl
            )
        }
        fun linkToDTO(link: Link): LinkDTO{
            return LinkDTO(
                idLink = link.idLink,
                username = link.username,
                title = link.title,
                url = link.url
            )
        }
        fun linkFromDTO(linkDTO: LinkDTO): Link{
            return Link(
                idLink = linkDTO.idLink,
                username = linkDTO.username,
                title = linkDTO.title,
                url = linkDTO.url
            )
        }

        fun sponsorToDTO(sponsor: Sponsor): SponsorDTO{
            return SponsorDTO(
                idSponsor = sponsor.idSponsor,
                username = sponsor.username,
                title = sponsor.title,
                description = sponsor.description,
                phone = sponsor.phone,
                urlImage = sponsor.urlImage
            )
        }
        fun sponsorFromDTO(sponsorDTO: SponsorDTO): Sponsor{
            return Sponsor(
                idSponsor = sponsorDTO.idSponsor,
                username = sponsorDTO.username,
                title = sponsorDTO.title,
                description = sponsorDTO.description,
                phone = sponsorDTO.phone,
                urlImage = sponsorDTO.urlImage
            )
        }

        fun adToDTO(ad: Ad): AdDTO{
            return AdDTO(
                idAd = ad.idAd,
                username = ad.username,
                title = ad.title,
                description = ad.description,
                imageUrl = ad.imageUrl,
                webUrl = ad.webUrl
            )
        }
        fun adFromDTO(adDTO: AdDTO): Ad{
            return Ad(
                idAd = adDTO.idAd,
                username = adDTO.username,
                title = adDTO.title,
                description = adDTO.description,
                imageUrl = adDTO.imageUrl,
                webUrl = adDTO.webUrl
            )
        }
    }
}