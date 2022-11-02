package com.naukma.springproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringProjectApplication implements CommandLineRunner {

    Logger logger = LogManager.getLogger("FileLogger");

    public static void main(String[] args) {
        SpringApplication.run(SpringProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ThreadContext.put("details", "");
        logger.info(MarkerManager.getMarker("IMPORTANT"), "MARKER TEST HELLOOOO");
        ThreadContext.clearAll();
    }
}
