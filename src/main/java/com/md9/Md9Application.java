package com.md9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.md9")
@EnableMongoRepositories
public class Md9Application {
    public static void main(String[] args) {
        SpringApplication.run(Md9Application.class, args);
    }
}
