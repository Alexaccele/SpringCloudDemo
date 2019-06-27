package com.example.getinfoservice.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class InfoDiscoveryClient {
    @Autowired
    private DiscoveryClient discoveryClient;

    public String getInfo(String name){
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("discovery-getinfo");
        if(instances.size() == 0)return null;
        String serviceUri = String.format("%s/getInfo/%s",
                instances.get(0).getUri().toString(),
                name);
        System.out.println(serviceUri);
        ResponseEntity<String> exchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null, String.class, name);
        return exchange.getBody();
    }
}
