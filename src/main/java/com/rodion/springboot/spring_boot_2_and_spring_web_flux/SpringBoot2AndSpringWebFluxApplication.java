package com.rodion.springboot.spring_boot_2_and_spring_web_flux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"com.rodion.springboot.spring_boot_2_and_spring_web_flux.repository"})
public class SpringBoot2AndSpringWebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2AndSpringWebFluxApplication.class, args);
    }
}
