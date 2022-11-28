package com.etno.microservice.util

import com.etno.microservice.model.*
import com.etno.microservice.model.dto.*

class DataConverter {
    companion object {
        fun userToDTO(user: User): UserDTO {
            return UserDTO(
                idUser = user.idUser,
                username = user.username,
                password = user.password,
                role = user.role,
                events = user.events.let { it!!.map { event -> eventToDTO(event) } }.toMutableList()
            )
        }

        fun userFromDTO(userDTO: UserDTO): User {
            return User(
                idUser = userDTO.idUser!!,
                username = userDTO.username,
                password = userDTO.password,
                role = userDTO.role,
                events = userDTO.events.let { it?.map { eventDTO -> eventFromDTO(eventDTO) } }!!.toMutableList()
            )
        }

        fun eventToDTO(event: Event): EventDTO {
            return EventDTO(
                idEvent = event.idEvent,
                title = event.title,
                address = event.address,
                description = event.description,
                organization = event.organization,
                link = event.link,
                startDate = event.startDate,
                endDate = event.endDate,
                publicationDate = event.publicationDate,
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
                title = eventDTO.title,
                address = eventDTO.address,
                description = eventDTO.description,
                organization = eventDTO.organization,
                link = eventDTO.link,
                startDate = eventDTO.startDate,
                endDate = eventDTO.endDate,
                publicationDate = eventDTO.publicationDate,
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
                title = tourism.title,
                description = tourism.description,
                images = tourism.images.let { tourism.images?.map { image -> imageToDTO(image) } }!!.toMutableList(),
                latitude = tourism.latitude,
                longitude = tourism.longitude
            )
        }
        fun tourismFromDTO(tourismDTO: TourismDTO): Tourism{
            return Tourism(
                idTourism = tourismDTO.idTourism,
                title = tourismDTO.title,
                description = tourismDTO.description,
                images = tourismDTO.images.let { tourismDTO.images?.map { imageDTO -> imageFromDTO(imageDTO) } }?.toMutableList(),
                latitude = tourismDTO.latitude,
                longitude = tourismDTO.longitude
            )
        }
    }
}