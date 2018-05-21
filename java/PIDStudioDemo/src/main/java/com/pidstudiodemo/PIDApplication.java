package com.pidstudiodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ServletComponentScan
@EnableJpaRepositories(basePackages = "com.pidstudiodemo.common.jpa")
@EntityScan(basePackages = "com.pidstudiodemo")
public class PIDApplication {
    public static void main(String[] args) {
        SpringApplication.run(PIDApplication.class, args);
    }
}		