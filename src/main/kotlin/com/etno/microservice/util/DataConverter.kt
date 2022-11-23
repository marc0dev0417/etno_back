package com.etno.microservice.util

import com.etno.microservice.model.Event
import com.etno.microservice.model.User
import com.etno.microservice.model.dto.EventDTO
import com.etno.microservice.model.dto.UserDTO

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
            )
        }

        fun eventFromDTO(eventDTO: EventDTO): Event {
            return Event(
                idEvent = eventDTO.idEvent!!,
                title = eventDTO.title,
            )
        }
    }
}