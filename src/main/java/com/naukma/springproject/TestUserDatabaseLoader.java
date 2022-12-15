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
            UserEntity user3 = new UserEntity("andriy", passwordEncoder.encode("pass"), "Andriy", "Videyko", Role.STUDENT, "andriy@ukma.edu.ua");

            userRepository.saveAll(List.of(user1,user2,user3));

            Organization org1 = new Organization();
            org1.setName("Photo Club");
            Organization org2 = new Organization();
            org2.setName("IT Club");
            Organization org3 = new Organization();
            org3.setName("Art Club");
            organizationService.register(org1);
            organizationService.register(org2);
            organizationService.register(org3);

            organizationService.addStudent("Photo Club", "andriy");
            organizationService.addStudent("IT Club", "andriy");
            organizationService.addStudent("Art Club", "andriy");

            Project proj1 = new Project();
            proj1.setName("Photographing courses");
            Project proj2 = new Project();
            proj2.setName("Party Evening");
            Project proj3 = new Project();
            proj3.setName("Student Activities");
            Project proj4 = new Project();
            proj4.setName("C++ Courses");
            Project proj5 = new Project();
            proj5.setName("Hackathon Sep2022");
            Project proj6 = new Project();
            proj6.setName("Music Processing Soft");
            Project proj7 = new Project();
            proj7.setName("Painting Courses");
            Project proj8 = new Project();
            proj8.setName("3D Modelling");
            Project proj9 = new Project();
            proj9.setName("Art Exhibition");

            projectService.addTo(proj1, "Photo Club");
            projectService.addTo(proj2, "Photo Club");
            projectService.addTo(proj3, "Photo Club");
            projectService.addTo(proj4, "IT Club");
            projectService.addTo(proj5, "IT Club");
            projectService.addTo(proj6, "IT Club");
            projectService.addTo(proj7, "Art Club");
            projectService.addTo(proj8, "Art Club");
            projectService.addTo(proj9, "Art Club");

            projectService.addStudent("Photographing courses", "andriy");
            projectService.addStudent("Party Evening", "andriy");
            projectService.addStudent("Student Activities", "andriy");
            projectService.addStudent("C++ Courses", "andriy");
            projectService.addStudent("Hackathon Sep2022", "andriy");
            projectService.addStudent("Music Processing Soft", "andriy");
            projectService.addStudent("Painting Courses", "andriy");
            projectService.addStudent("3D Modelling", "andriy");
            projectService.addStudent("Art Exhibition", "andriy");
        };
    }
}
