package org.vosiievska.bicycle.service.workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.vosiievska.bicycle.service")
public class WorkshopServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkshopServiceApplication.class, args);
    }
}