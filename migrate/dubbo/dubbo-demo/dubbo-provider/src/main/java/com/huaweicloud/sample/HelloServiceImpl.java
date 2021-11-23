package com.huaweicloud.sample;

import org.apache.dubbo.config.annotation.DubboService;

import com.huaweicloud.sample.api.HelloService;

@DubboService
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello " + name + ", I am from provider";
    }
}
