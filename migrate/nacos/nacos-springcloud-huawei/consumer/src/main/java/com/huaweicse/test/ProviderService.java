package com.huaweicse.test;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "nacos-provider",contextId = "providerService")
public interface ProviderService {
    @GetMapping(value = "/sayHello")
    String sayHello(@RequestParam("name") String name);
}
