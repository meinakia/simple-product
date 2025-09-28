package org.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SimpleProductJavaApp {
    public static void main(String[] args) {
        System.out.printf("Starting SimpleProductJavaApp!");
        SpringApplication.run(SimpleProductJavaApp.class, args);
    }
}