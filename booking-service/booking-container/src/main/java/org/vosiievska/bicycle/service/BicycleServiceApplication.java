package org.vosiievska.bicycle.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.vosiievska.bicycle.service")
public class BicycleServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BicycleServiceApplication.class, args);
  }
}
