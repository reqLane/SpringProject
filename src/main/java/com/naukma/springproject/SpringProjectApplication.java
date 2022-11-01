package com.naukma.springproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringProjectApplication implements CommandLineRunner {

    Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        SpringApplication.run(SpringProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("MESSAGE 222");
    }
}
