package org.vosiievska.bicycle.service.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = "org.vosiievska.bicycle.service.payment")
@SpringBootApplication(scanBasePackages = "org.vosiievska.bicycle.service")
public class PaymentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}