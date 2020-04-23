package com.example.someservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @ClassName ResourceServerConfig
 * @Author zrx
 * @Description //TODO
 * @Date 2020/4/23 16:36
 **/
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/admin")
                .hasRole("ADMIN")//只有具有ADMIN角色的用户才能访问/admin
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();//所有请求必须是通过验证的用户才能访问
    }
}
