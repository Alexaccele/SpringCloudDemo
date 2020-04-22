package com.example.zuulservicegateway.filters;

import com.example.zuulservicegateway.utils.HeaderUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CheckHeaderPreFilter extends ZuulFilter {
    @Autowired
    private HeaderUtil headerUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        String key = headerUtil.getHeaderKey();
        if(key != null){
            log.info("header contains key : {}",key);
        }else{
            key = headerUtil.generateKey();
            headerUtil.setKeyToHeader(key);
            log.info("generate key is {}",key);
        }
        return null;
    }
}
