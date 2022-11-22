package com.etno.microservice.util

import com.etno.microservice.model.Event
import com.etno.microservice.model.User
import com.etno.microservice.model.dto.EventDTO
import com.etno.microservice.model.dto.UserDTO

object DataConverter {
    fun userToDTO(user: User): UserDTO{
        return UserDTO(
            id = user.id,
            username = user.username,
            password = user.password,
            role = user.role,
           // list = user.list.let { it?.map { event -> eventToDTO(event) } }!!.toMutableList()
        )
    }
    fun userFromDTO(userDTO: UserDTO): User{
        return User(
            id = userDTO.id,
            username = userDTO.username,
            password = userDTO.password,
            role = userDTO.role,
            //list = userDTO.list.let { it?.map { eventDTO ->  eventFromDTO(eventDTO)} }!!.toMutableList()
        )
    }

    fun eventToDTO(event: Event): EventDTO{
        return EventDTO(
            id = event.id,
            title = event.title,

        )
    }
    fun eventFromDTO(eventDTO: EventDTO): Event{
        return Event(
            id = eventDTO.id,
            title = eventDTO.title,
        )
    }


}