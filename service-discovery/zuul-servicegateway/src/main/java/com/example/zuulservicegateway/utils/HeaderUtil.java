package com.example.zuulservicegateway.utils;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @ClassName HeaderUtil
 * @Author zrx
 * @Description 关于header信息的工具
 * @Date 2020/4/22 20:37
 **/
@Component
public class HeaderUtil {
    public void setKeyToHeader(String key) {
        RequestContext context = RequestContext.getCurrentContext();
        context.addZuulRequestHeader("key",key);
    }

    public String generateKey(){
        return UUID.randomUUID().toString();
    }

    public String getHeaderKey(){
        RequestContext context = RequestContext.getCurrentContext();
        String key = context.getRequest().getHeader("key");
        if(key == null){
            key = context.getZuulRequestHeaders().get("key");
        }
        return key;
    }
}
