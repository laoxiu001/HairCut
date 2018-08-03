package com.pidstudiodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ServletComponentScan
@EnableJpaRepositories(basePackages = "com.pidstudiodemo.common.jpa")
@EntityScan(basePackages = "com.pidstudiodemo")
public class PIDApplication  {
//    public static void main(String[] args) {
//        SpringApplication.run(PIDApplication.class, args);
//    }
    public static void main(String[] args) {
        SpringApplication.run(PIDApplication.class, args);
    }
//	@Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        // 注意这里要指向原先用main方法执行的Application启动类
//        return builder.sources(PIDApplication.class);
//    }
}		