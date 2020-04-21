package com.example.discoverygetinfo.discovery;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("getinfo-service")
public interface InfoFeignClient {
    @GetMapping(value = "/getInfo/{name}")
    String getInfoByDiscovery(@PathVariable String name);
}
