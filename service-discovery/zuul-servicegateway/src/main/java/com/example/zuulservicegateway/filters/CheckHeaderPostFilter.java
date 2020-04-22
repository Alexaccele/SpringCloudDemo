package com.example.zuulservicegateway.filters;

import com.example.zuulservicegateway.utils.HeaderUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName CheckHeaderPostFilter
 * @Author zrx
 * @Description //验证前置过滤器设置的key是否能够获取，并响应
 * @Date 2020/4/22 20:29
 **/
@Slf4j
public class CheckHeaderPostFilter extends ZuulFilter {
    @Autowired
    private HeaderUtil headerUtil;

    @Override
    public String filterType() {
        return "post";
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
        RequestContext context = RequestContext.getCurrentContext();
        String key = headerUtil.getHeaderKey();
        log.info("post filter check the key is {}",key);
        context.getResponse().addHeader("key",key);
        return null;
    }
}
