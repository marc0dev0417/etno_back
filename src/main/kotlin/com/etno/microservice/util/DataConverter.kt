package com.etno.microservice.util

import com.etno.microservice.model.Event
import com.etno.microservice.model.Image
import com.etno.microservice.model.User
import com.etno.microservice.model.Video
import com.etno.microservice.model.dto.EventDTO
import com.etno.microservice.model.dto.ImageDTO
import com.etno.microservice.model.dto.UserDTO
import com.etno.microservice.model.dto.VideoDTO

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
                description = event.description,
                startDate = event.startDate,
                endDate = event.endDate,
                publicationDate = event.publicationDate,
                latitude = event.latitude,
                longitude = event.longitude,
                subscription = event.subscription,
                images = event.images.let { event.images?.map { image -> imageToDTO(image) } }?.toMutableList(),
                videos = event.videos.let { event.videos?.map { video -> videoToDTO(video) } }?.toMutableList()
            )
        }

        fun eventFromDTO(eventDTO: EventDTO): Event {
            return Event(
                idEvent = eventDTO.idEvent!!,
                title = eventDTO.title,
                description = eventDTO.description,
                startDate = eventDTO.startDate,
                endDate = eventDTO.endDate,
                publicationDate = eventDTO.publicationDate,
                latitude = eventDTO.latitude,
                longitude = eventDTO.longitude,
                subscription = eventDTO.subscription,
                images = eventDTO.images.let { eventDTO.images?.map { imageDTO -> imageFromDTO(imageDTO) } }?.toMutableList(),
                videos = eventDTO.videos.let { eventDTO.videos?.map { videoDTO -> videoFromDTO(videoDTO) } }?.toMutableList()
            )
        }

        fun imageToDTO(image: Image): ImageDTO{
            return ImageDTO(
                idImage = image.idImage,
                link = image.link
            )
        }
        fun imageFromDTO(imageDTO: ImageDTO): Image{
            return Image(
                idImage = imageDTO.idImage!!,
                link = imageDTO.link
            )
        }

        fun videoToDTO(video: Video): VideoDTO{
            return VideoDTO(
                idVideo = video.idVideo,
                link = video.link
            )
        }
        fun videoFromDTO(videoDTO: VideoDTO): Video{
            return Video(
                idVideo = videoDTO.idVideo,
                link = videoDTO.link
            )
        }
    }
}