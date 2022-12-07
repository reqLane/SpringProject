package com.naukma.springproject.tests;


import com.google.gson.Gson;
import com.naukma.springproject.controller.StudentController;
import com.naukma.springproject.model.User;

import com.naukma.springproject.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
//@SpringBootTest
@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(StudentController.class)
public class UserCreateAndDeleteTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Test
    @WithMockUser
    public void createAndDeleteUserTest() throws Exception {
        assertNotNull(mockMvc);

        User user = new User("testUser", "b2estUser", "Ivan", "Pavlov", "ivanPav@gmail.com");

        Gson gson = new Gson();
        String json = gson.toJson(user);

        mockMvc.perform(get("/student/info")).andExpect(status().isOk());


        mockMvc.perform(post("/student/register")
                        .contentType("application/json")
                        .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/student/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}