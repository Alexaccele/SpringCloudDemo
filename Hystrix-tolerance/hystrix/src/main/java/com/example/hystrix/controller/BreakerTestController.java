package com.example.hystrix.controller;

import com.example.hystrix.services.SomethingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class BreakerTestController {
    @Autowired
    SomethingService service;

    @GetMapping("/get/{key}")
    public String getString(@PathVariable("key") String key){
        return service.someService(key);
    }
}
