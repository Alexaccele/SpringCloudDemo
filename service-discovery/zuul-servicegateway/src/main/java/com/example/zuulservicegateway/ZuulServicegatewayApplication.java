package com.example.zuulservicegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy    //服务路由代理
public class ZuulServicegatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServicegatewayApplication.class, args);
    }

}
