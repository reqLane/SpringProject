package com.naukma.springproject;

import com.naukma.springproject.entity.UserEntity;
import com.naukma.springproject.enums.Role;
import com.naukma.springproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class TestUserDatabaseLoader {

    private UserRepository repo;

    public TestUserDatabaseLoader(UserRepository repo){
        this.repo = repo;
    }

    @Bean
    public CommandLineRunner initializeDatabase(PasswordEncoder passwordEncoder){
        return args -> {
            UserEntity user1 = new UserEntity("anatolii72", passwordEncoder.encode("the9BestPassw0rd"), "Anatolii", "Andrusenko", Role.STUDENT, "anatolii.andrusenko@ukma.edu.ua");
            UserEntity user2 = new UserEntity("march", passwordEncoder.encode("pass"), "Vladyslav", "Marchenko", Role.ADMIN, "march@gmail.com");
            UserEntity user3 = new UserEntity("andriy", passwordEncoder.encode("123"), "Andriy", "Videyko", Role.STUDENT, "andriy@ukma.edu.ua");

            repo.saveAll(List.of(user1,user2,user3));
        };
    }
}
