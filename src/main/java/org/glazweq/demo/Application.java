package org.glazweq.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@EnableCaching
@SpringBootApplication
public class Application {
    public String PORT = System.getenv("PORT");

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}