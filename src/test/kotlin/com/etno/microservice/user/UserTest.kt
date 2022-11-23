package com.etno.microservice.user

import com.etno.microservice.model.User
import com.etno.microservice.model.dto.EventDTO
import com.etno.microservice.model.dto.UserDTO
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@SpringBootTest
@AutoConfigureMockMvc
class UserTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    private var gson:Gson = Gson()

    lateinit var token: String

    @Test
    fun saveUser(){

        val userDTO = UserDTO(
            null,
            "admin",
            "12345",
            "super",
            mutableListOf(EventDTO(idEvent = null, title = "event 1")),
            )

        val objectUser:String = gson.toJson(userDTO, UserDTO::class.java)

        val result = mockMvc.perform(
            MockMvcRequestBuilders.post("/register")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectUser)).andReturn().response.contentAsString

        val valueUser: User = gson.fromJson(result, User::class.java)

        Assertions.assertEquals(valueUser.username, userDTO.username)
    }
    @Test
    fun login(){
        val result = mockMvc.perform(
            MockMvcRequestBuilders.get("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("username", "admin")
                .param("password", "12345"))
                .andReturn().response.contentAsString

        val valueUser: User = gson.fromJson(result, User::class.java)

        Assertions.assertEquals(valueUser.username, "admin")
    }
}