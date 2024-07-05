package com.spring.mvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createPerson() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Arbi")
                        .param("middleName", "Dwi")
                        .param("lastName", "Wijaya")
                        .param("email", "arbi@gmail.com")
                        .param("phone", "01239874567")
                        .param("address.street", "Jalan Peltu Sujono")
                        .param("address.city", "Malang")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "65148")
                        .param("hobbies[0]", "Coding")
                        .param("hobbies[1]", "Gaming")
                        .param("socialMedias[0].name", "Twitter")
                        .param("socialMedias[0].location", "x.com/kalista69666")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString
                        ("Success create person Arbi Dwi Wijaya " +
                        "with email arbi@gmail.com and phone 01239874567 " +
                        "with address Jalan Peltu Sujono, Malang, Indonesia, 65148"))
        );
    }

    @Test
    void createPersonInvalid() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("middleName", "Dwi")
                        .param("lastName", "Wijaya")
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("Validation Error"))
        );
    }
}