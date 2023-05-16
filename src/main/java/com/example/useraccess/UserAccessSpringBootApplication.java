package com.example.useraccess;

import com.example.useraccess.service.FilesStorageService;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import org.slf4j.Logger;

@SpringBootApplication
public class UserAccessSpringBootApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(UserAccessSpringBootApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UserAccessSpringBootApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {

    }
}