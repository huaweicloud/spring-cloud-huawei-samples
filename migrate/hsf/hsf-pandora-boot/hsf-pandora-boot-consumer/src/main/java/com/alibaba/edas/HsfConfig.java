package com.alibaba.edas;

import com.alibaba.boot.hsf.annotation.HSFConsumer;

import org.springframework.context.annotation.Configuration;


@Configuration
public class HsfConfig {

    @HSFConsumer(clientTimeout = 3000, serviceGroup = "hsf-pandora-boot-provider", serviceVersion = "1.0.0")
    private HelloService helloService;

}
