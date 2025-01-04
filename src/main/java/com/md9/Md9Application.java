package com.md9;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.ConfigurableApplicationContext;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// @SpringBootApplication
// @EnableJpaRepositories(basePackages = "com.md9.repository")
// public class Md9Application {
    
//     public static void main(String[] args) {
//         ConfigurableApplicationContext context = SpringApplication.run(Md9Application.class, args);
//         Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//             context.close();
//         }));
//     }
// }
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
