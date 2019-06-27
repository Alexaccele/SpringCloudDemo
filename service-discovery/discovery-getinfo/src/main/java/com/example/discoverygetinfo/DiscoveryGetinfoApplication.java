package com.example.discoverygetinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class DiscoveryGetinfoApplication {
    @GetMapping("/getInfo/{name}")
    public String getInfo(@PathVariable String name){
        return "It's " + name + "'s info: ......";
    }
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryGetinfoApplication.class, args);
    }

}
