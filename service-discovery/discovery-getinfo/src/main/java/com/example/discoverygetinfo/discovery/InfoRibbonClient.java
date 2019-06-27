package com.example.discoverygetinfo.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InfoRibbonClient {
    @LoadBalanced
    @Bean
    private RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    public String getInfo(String name){
        ResponseEntity<String> exchange = restTemplate.exchange("http://getifo-service/getInfo/{name}",
                HttpMethod.GET,
                null, String.class, name);
        return exchange.getBody();
    }
}
