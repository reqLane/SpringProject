package com.naukma.springproject.tests;

import com.google.gson.Gson;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.naukma.springproject.entity.UserEntity;
import com.naukma.springproject.enums.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserPermissionsTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(authorities = {"STUDENT"})
    public void testsOfUsersPermissions() throws Exception {
        String login = "testUser";
        String name = "Ivan";
        UserEntity userEntity = new UserEntity(login, "b2estUser", name, "Pavlov", Role.STUDENT, "ivanPav@gmail.com");
        Gson gson = new Gson();
        String json = gson.toJson(userEntity);
        mvc.perform(post("/student/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                        )
                .andExpect(status().isOk());

        UserEntity userEntity1 = new UserEntity("secondUser", "b2estUser", name, "Pavlov", Role.STUDENT, "ivanPav@gmail.com");
        String json1 = gson.toJson(userEntity1);

        mvc.perform(post("/admin/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json1)
                )
                .andExpect(status().isForbidden());

        mvc.perform(get("/student/get-by-login/"+login)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)));


    }
}
