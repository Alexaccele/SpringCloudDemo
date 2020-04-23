package com.example.securityoauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableAuthorizationServer  //本服务作为OAuth2服务
@EnableResourceServer
@RestController
public class SecurityOauth2Application {
    @GetMapping(value = "/user",produces = "application/json")
    public Map<String,Object> user(OAuth2Authentication user){
        Map<String,Object> users = new HashMap<>();
        users.put("user",user.getUserAuthentication().getPrincipal());
        users.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        return users;
    }
    public static void main(String[] args) {
        SpringApplication.run(SecurityOauth2Application.class, args);
    }

}
