package com.example.someservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableResourceServer //受保护资源
@RestController
public class SomeServiceApplication {

    @GetMapping("/")
    public String hello(){
        return "hello world";
    }
    @GetMapping("/admin")
    public String root(){
        return "admin resource";
    }

    public static void main(String[] args) {
        SpringApplication.run(SomeServiceApplication.class, args);
    }

}
