package com.etno.microservice.user

import com.etno.microservice.model.dto.EventDTO
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import javax.lang.model.type.ArrayType

@SpringBootTest
@AutoConfigureMockMvc
class EventTest {
    @Autowired
    private lateinit var mockMvc: MockMvc
    private var gson: Gson = Gson()

    @Test
    fun saveEvent(){
        val eventDTO = EventDTO(
            idEvent = null,
            title = "Ir al cine",
            address = "Huesca, Aragon",
            description = "Ver una peli",
            organization = "Ecomputer",
            link = "https://ecomputer.es",
            startDate = "28/11/2022",
            endDate = "30/11/2022",
            time = "12 PM",
            lat = "1234353242",
            long = "-234123122",
        )

        val eventToJson = gson.toJson(eventDTO, eventDTO::class.java)

        val result = mockMvc.perform(
            MockMvcRequestBuilders.post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(eventToJson)).andReturn().response.contentAsString

        val resultEvent = gson.fromJson(result, EventDTO::class.java)

        Assertions.assertEquals(resultEvent.title, "Ir al cine")
        Assertions.assertEquals(resultEvent.address, "Huesca, Aragon")
        Assertions.assertEquals(resultEvent.description, "Ver una peli")
        Assertions.assertEquals(resultEvent.organization, "Ecomputer")
        Assertions.assertEquals(resultEvent.link, "https://ecomputer.es")
        Assertions.assertEquals(resultEvent.startDate, "28/11/2022")
        Assertions.assertEquals(resultEvent.endDate, "30/11/2022")
        Assertions.assertEquals(resultEvent.time, "12 PM")
        Assertions.assertEquals(resultEvent.lat, "1234353242")
        Assertions.assertEquals(resultEvent.long, "-234123122")
    }

    @Test
    fun getEvents(){
        val result = mockMvc.perform(
            MockMvcRequestBuilders.get("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn().response.contentAsString

        val eventList: Array<EventDTO> = gson.fromJson(result, Array<EventDTO>::class.java)

        Assertions.assertTrue(eventList.isNotEmpty())
    }
}