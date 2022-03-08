package com.dev.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.dev.springboot.repo")
@EntityScan("com.dev.springboot.model")
@ComponentScan({"com.dev.springboot.service", "com.dev.springboot.exception", "com.dev.springboot.controller"})
public class OrgMvc2Application {

    public static void main(String[] args) {
        SpringApplication.run(OrgMvc2Application.class, args);
    }

}
