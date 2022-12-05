package com.naukma.springproject.tests;

import com.naukma.springproject.controller.OrganizationController;
import com.naukma.springproject.entity.OrganizationEntity;
import com.naukma.springproject.repository.OrganizationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.times;


@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = OrganizationController.class)
@Import(OrganizationController.class)
public class OrganizationTest {

    @Autowired
    private WebTestClient webClient;
    @MockBean
    OrganizationRepository repository;

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    void createOrganizationTest() {
        OrganizationEntity organizationEntity = new OrganizationEntity();
        String name = "newOrganization";
        organizationEntity.setName(name);

        webClient.post()
                .uri("/organization/register")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(organizationEntity), OrganizationEntity.class)
                .exchange().expectStatus().isOk();

        webClient.get()
                .uri("/organization/get/1")
                .exchange()
                .expectStatus().isOk();
    }

//    @Test
//    void testGetEmployeesByName()
//    {
//        Employee employee = new Employee();
//        employee.setId(1);
//        employee.setName("Test");
//        employee.setSalary(1000);
//
//        List<Employee> list = new ArrayList<Employee>();
//        list.add(employee);
//
//        Flux<Employee> employeeFlux = Flux.fromIterable(list);
//
//        Mockito
//                .when(repository.findByName("Test"))
//                .thenReturn(employeeFlux);
//
//        webClient.get().uri("/name/{name}", "Test")
//                .header(HttpHeaders.ACCEPT, "application/json")
//                .exchange()
//                .expectStatus().isOk()
//                .expectBodyList(Employee.class);
//
//        Mockito.verify(repository, times(1)).findByName("Test");
//    }
}
