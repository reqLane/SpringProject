package com.naukma.springproject.tests;


import com.google.gson.Gson;
import com.naukma.springproject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserCreateAndDeleteTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void createAndDeleteUserTest() throws Exception {
        assertNotNull(mockMvc);

        User user = new User("testUser", "b2estUser", "Ivan", "Pavlov", "ivanPav@gmail.com");

        Gson gson = new Gson();
        String json = gson.toJson(user);

        mockMvc.perform(get("/student/info")).andExpect(status().isOk());

        mockMvc.perform(post("/student/register-test")
                        .contentType("application/json")
                        .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/student/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}