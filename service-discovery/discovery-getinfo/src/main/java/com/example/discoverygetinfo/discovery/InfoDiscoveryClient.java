package com.example.discoverygetinfo.discovery;

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
        //获取对应服务的所有实例列表
        List<ServiceInstance> instances = discoveryClient.getInstances("getinfo-service");
        if(instances.size() == 0)return null;
        String serviceUri = String.format("%s/getInfo/%s",
                instances.get(0).getUri().toString(),
                name);//得到服务实例位置
        //使用标准的Spring REST模板类调用该服务实例
        ResponseEntity<String> exchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null, String.class, name);
        return exchange.getBody();
    }
}
