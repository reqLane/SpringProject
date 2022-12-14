package com.naukma.springproject.tests;

import com.google.gson.Gson;
import com.naukma.springproject.model.Organization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class OrganizationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(authorities = "ADMIN")
    void createOrganizationTest() throws Exception {

        Organization organization = new Organization();
        String name = "newOrganization";
        organization.setName(name);
        String json = new Gson().toJson(organization);

        mockMvc.perform(post("/organization/register-test")
                        .contentType("application/json")
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/organization/get/newOrganization"))
                        .andExpect(status().isOk());
    }
}
