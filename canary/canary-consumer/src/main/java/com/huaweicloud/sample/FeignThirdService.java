package com.huaweicloud.sample;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "thirdService")
public interface FeignThirdService {

  @GetMapping("/hello")
  String sayHelloFeignThird(@RequestParam("name") String name);
}
