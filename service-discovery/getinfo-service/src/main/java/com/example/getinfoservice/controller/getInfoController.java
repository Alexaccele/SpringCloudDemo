package com.example.getinfoservice.controller;

import com.example.getinfoservice.discovery.InfoDiscoveryClient;
import com.example.getinfoservice.discovery.InfoRibbonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class getInfoController {
    @Autowired
    InfoDiscoveryClient discoveryClient;
    @Autowired
    InfoRibbonClient ribbonClient;
    @GetMapping("/getInfo/{name}/{type}")
    private String getInfoByDiscovery(@PathVariable String name, @PathVariable(name = "type") String discoveryType){
        String info = null;
        switch (discoveryType){
            case "discovery":
                System.out.println("use discovery client");
                info = discoveryClient.getInfo(name);
                break;
            case "ribbon":
                System.out.println("use ribbon client");
                info = ribbonClient.getInfo(name);
                break;
            default:
                info = discoveryClient.getInfo(name);
        }
        return info;
    }
}