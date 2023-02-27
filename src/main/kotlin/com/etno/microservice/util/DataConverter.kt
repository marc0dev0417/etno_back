package com.etno.microservice.util

import com.etno.microservice.model.*
import com.etno.microservice.model.dto.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class DataConverter {
    companion object {

        fun userLoginToDTO(user: User): UserLoginDTO{
            return UserLoginDTO(
                idUser = user.idUser,
                username = user.username,
                password = user.password
            )
        }
        fun userToDTO(user: User): UserDTO {
            return UserDTO(
                idUser = user.idUser,
                username = user.username,
                password = user.password,
                events = user.events.let { it!!.map { event -> eventToDTO(event) } }.toMutableList(),
                pharmacies = user.pharmacies.let { it?.map { pharmacy -> pharmacyToDTO(pharmacy) } }?.toMutableList(),
                tourism = user.tourism.let { it?.map { tourism -> tourismToDTO(tourism) } }?.toMutableList(),
                deaths = user.deaths.let { it?.map { death -> deathToDTO(death) } }?.toMutableList(),
                services = user.services.let { it?.map { service -> serviceToDTO(service) } }?.toMutableList(),
                news = user.news.let { it?.map { new -> newsToDTO(new) } }?.toMutableList(),
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
                services = userDTO.services.let { it?.map { serviceDTO -> serviceFromDTO(serviceDTO) } }?.toMutableList(),
                news = userDTO.news.let { it?.map { newDTO -> newsFromDTO(newDTO) } }?.toMutableList(),
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
                services = user.services.let { it?.map { service -> serviceToDTO(service) } }?.toMutableList(),
                news = user.news.let { it?.map { new -> newsToDTO(new) } }?.toMutableList(),
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
                latitude = pharmacy.latitude,
                startDate = pharmacy.startDate,
                durationDays = pharmacy.durationDays,
                frequencyInDays = pharmacy.frequencyInDays,
                dates = pharmacy.dates.let { pharmacy.dates?.map { pharmacyDate -> pharmacyDateToDTO(pharmacyDate) } }?.toMutableList()
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
                latitude = pharmacyDTO.latitude,
                startDate = pharmacyDTO.startDate,
                durationDays = pharmacyDTO.durationDays,
                frequencyInDays = pharmacyDTO.frequencyInDays,
                dates = pharmacyDTO.dates.let { pharmacyDTO.dates?.map { pharmacyDateDTO -> pharmacyDateFromDTO(pharmacyDateDTO) } }?.toMutableList()
            )
        }

        fun pharmacyDateToDTO(pharmacyDate: PharmacyDate): PharmacyDateDTO {
            return PharmacyDateDTO(
                idPharmacyDate = pharmacyDate.idPharmacyDate,
                username = pharmacyDate.username,
                namePharmacy = pharmacyDate.namePharmacy,
                date = pharmacyDate.date
            )
        }
        fun pharmacyDateFromDTO(pharmacyDateDTO: PharmacyDateDTO): PharmacyDate {
            return PharmacyDate(
                idPharmacyDate = pharmacyDateDTO.idPharmacyDate,
                username = pharmacyDateDTO.username,
                namePharmacy = pharmacyDateDTO.namePharmacy,
                date = pharmacyDateDTO.date
            )
        }

         fun sumDate(date: Date, days: Int): Date? {
            val calendar = Calendar.getInstance();
            calendar.time = date; // Configuramos la fecha que se recibe
            calendar.add(Calendar.DAY_OF_YEAR, days);  // numero de días a añadir, o restar en caso de días<0
            return calendar.time; // Devuelve el objeto Date con los nuevos días añadidos
        }
         fun getMounth(date: Date): Int {
            val calendar = Calendar.getInstance()
            calendar.time = date;
            return calendar.get(Calendar.MONTH) + 1;
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

        fun serviceToDTO(service: Service): ServiceDTO{
            return ServiceDTO(
                idService = service.idService,
                username = service.username,
                category = service.category,
                owner = service.owner,
                number = service.number,
                description = service.description,
                webUrl = service.webUrl,
                schedule = service.schedule,
                imageUrl = service.imageUrl
            )
        }
        fun serviceFromDTO(serviceDTO: ServiceDTO): Service{
            return Service(
                idService = serviceDTO.idService,
                username = serviceDTO.username,
                category = serviceDTO.category,
                owner = serviceDTO.owner,
                number = serviceDTO.number,
                description = serviceDTO.description,
                webUrl = serviceDTO.webUrl,
                schedule = serviceDTO.schedule,
                imageUrl = serviceDTO.imageUrl
            )
        }

        fun newsToDTO(news: News): NewsDTO {
            return NewsDTO(
                idNew = news.idNew,
                username = news.username,
                category = news.category,
                title = news.title,
                publicationDate = news.publicationDate,
                description = news.description,
                imageUrl = news.imageUrl
            )
        }
        fun newsFromDTO(newsDTO: NewsDTO): News{
            return News(
                idNew = newsDTO.idNew,
                username = newsDTO.username,
                category = newsDTO.category,
                title = newsDTO.title,
                publicationDate = newsDTO.publicationDate,
                description = newsDTO.description,
                imageUrl = newsDTO.imageUrl
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