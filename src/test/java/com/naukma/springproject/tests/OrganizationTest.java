package com.naukma.springproject.tests;

import com.naukma.springproject.config.SecurityConfig;
import com.naukma.springproject.entity.OrganizationEntity;
import com.naukma.springproject.model.Organization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@AutoConfigureMockMvc(addFilters = false)
@Import(SecurityConfig.class)
public class OrganizationTest {

    @Autowired
    private WebTestClient webClient;


    @Test
    void createOrganizationTest() {

        Organization organization = new Organization();
        String name = "newOrganization";
        organization.setName(name);

        webClient.post()
                .uri("/organization/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(organization), OrganizationEntity.class)
                .exchange().expectStatus().isOk();

        webClient.get()
                .uri("/organization/get/1")
                .exchange()
                .expectStatus().isOk();
    }
}
