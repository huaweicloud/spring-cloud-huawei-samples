package com.alibaba.edas;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.context.annotation.Configuration;


@Configuration
public class HsfConfig {

    @FeignClient(name = "hsf-pandora-boot-provider", contextId = "helloService", path = "/helloService")
    public interface HelloServiceExt extends HelloService{}

}
