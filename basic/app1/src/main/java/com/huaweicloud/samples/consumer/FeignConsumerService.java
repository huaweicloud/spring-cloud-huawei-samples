package com.huaweicloud.samples.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "core-gateway")
public interface FeignConsumerService {

    @GetMapping("/consumer/sayHelloFeign")
    String sayHelloFeign(@RequestParam("name") String name);
}
