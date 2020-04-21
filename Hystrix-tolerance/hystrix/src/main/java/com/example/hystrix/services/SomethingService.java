package com.example.hystrix.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SomethingService {
    private void doSomethingRandLongTime() {
        Random r = new Random();
        if(r.nextInt(3)+1 == 3){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "2000"),//定制2秒超时
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//熔断前十秒内，发生的连续调用数量
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "75"),//熔断前必须达到的错误调用百分比
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "7000"),//熔断后，允许某一请求通过调用的休眠周期
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "15000"),//监控服务调用问题的窗口大小
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets",value = "5")//窗口周期内收集统计信息的次数，注意metrics.rollingStats.timeInMilliseconds % numBuckets == 0
            },
            threadPoolKey = "someServiceThreadPool",//舱壁模式，线程隔离，指定线程池名称，避免部分服务影响整体服务
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize",value = "20"),//最大线程数
                    @HystrixProperty(name = "maxQueueSize",value = "10")//阻塞等待队列大小
            },
            fallbackMethod = "backService"//后备模式
    )
    public String someService(String key){
        doSomethingRandLongTime();
        return String.format("service success, and the key is %s",key);
    }
    //后备方法，参数与返回值必须与原方法相同，需要注意实际开发中，后备方案也可能出错，也可以再嵌套使用@HystrixCommand进行保护
    private String backService(String key){
        return "default key";
    }
}
