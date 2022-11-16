package com.naukma.springproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="StudentOrganizations"))
public class SpringProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringProjectApplication.class, args);
    }
}
