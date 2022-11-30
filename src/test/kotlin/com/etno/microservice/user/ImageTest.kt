package com.etno.microservice.user

import com.google.gson.Gson
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.io.File
import java.io.FileInputStream

@SpringBootTest
@AutoConfigureMockMvc
class ImageTest {
    @Autowired
    private lateinit var mockMvc: MockMvc
    private var gson: Gson = Gson()

    /*
    @Test
    fun saveImage(){

        //There is to put a configuration to give access to files ->

        val imageFile = File("C:\\Users\\marcobenegas\\Desktop\\app.png")
        val fileInputStream = FileInputStream(imageFile)
        val imageMultiPart = MockMultipartFile("image", "", "multipart/form-data", fileInputStream)

        val result = mockMvc.perform(multipart("/images").file(imageMultiPart)).andExpect(status().isOk)
    }
     */
}