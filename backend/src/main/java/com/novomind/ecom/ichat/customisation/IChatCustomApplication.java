package com.novomind.ecom.ichat.customisation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class IChatCustomApplication implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up().build();
    }

    public static void main(String[] args) {
        SpringApplication.run(IChatCustomApplication.class, args);
    }

}
