package com.naukma.springproject;

import com.naukma.springproject.entity.UserEntity;
import com.naukma.springproject.enums.Role;
import com.naukma.springproject.model.Organization;
import com.naukma.springproject.model.Project;
import com.naukma.springproject.repository.UserRepository;
import com.naukma.springproject.service.OrganizationService;
import com.naukma.springproject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class TestUserDatabaseLoader {

    private final UserRepository userRepository;
    private final OrganizationService organizationService;
    private final ProjectService projectService;

    @Autowired
    public TestUserDatabaseLoader(UserRepository userRepository, OrganizationService organizationService, ProjectService projectService){
        this.userRepository = userRepository;
        this.organizationService = organizationService;
        this.projectService = projectService;
    }

    @Bean
    public CommandLineRunner initializeDatabase(PasswordEncoder passwordEncoder){
        return args -> {
            UserEntity user1 = new UserEntity("anatolii72", passwordEncoder.encode("pass"), "Anatolii", "Andrusenko", Role.STUDENT, "anatolii.andrusenko@ukma.edu.ua");
            UserEntity user2 = new UserEntity("march", passwordEncoder.encode("pass"), "Vladyslav", "Marchenko", Role.ADMIN, "march@gmail.com");
            UserEntity user3 = new UserEntity("andriy", passwordEncoder.encode("123"), "Andriy", "Videyko", Role.STUDENT, "andriy@ukma.edu.ua");

            userRepository.saveAll(List.of(user1,user2,user3));

            Organization org1 = new Organization();
            org1.setName("Org_1st");
            Organization org2 = new Organization();
            org2.setName("Org_2nd");
            Organization org3 = new Organization();
            org3.setName("Org_3rd");
            organizationService.register(org1);
            organizationService.register(org2);
            organizationService.register(org3);

            organizationService.addStudent(1L, "andriy");
            organizationService.addStudent(2L, "andriy");
            organizationService.addStudent(3L, "andriy");

            Project proj1 = new Project();
            proj1.setName("proj1");
            Project proj2 = new Project();
            proj2.setName("proj2");
            Project proj3 = new Project();
            proj3.setName("proj3");
            Project proj4 = new Project();
            proj4.setName("proj4");
            Project proj5 = new Project();
            proj5.setName("proj5");
            Project proj6 = new Project();
            proj6.setName("proj6");
            Project proj7 = new Project();
            proj7.setName("proj7");
            Project proj8 = new Project();
            proj8.setName("proj8");
            Project proj9 = new Project();
            proj9.setName("proj9");

            projectService.addTo(proj1, 1L);
            projectService.addTo(proj2, 1L);
            projectService.addTo(proj3, 1L);
            projectService.addTo(proj4, 2L);
            projectService.addTo(proj5, 2L);
            projectService.addTo(proj6, 2L);
            projectService.addTo(proj7, 3L);
            projectService.addTo(proj8, 3L);
            projectService.addTo(proj9, 3L);

            projectService.addStudent(1L, "andriy");
            projectService.addStudent(2L, "andriy");
            projectService.addStudent(3L, "andriy");
            projectService.addStudent(4L, "andriy");
            projectService.addStudent(5L, "andriy");
            projectService.addStudent(6L, "andriy");
            projectService.addStudent(7L, "andriy");
            projectService.addStudent(8L, "andriy");
            projectService.addStudent(9L, "andriy");
        };
    }
}
