package com.huaweicloud.samples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
  @Autowired
  private ConsumerService consumerService;
  // consumer service which delegate the implementation to provider service.
  @GetMapping("/sayHi")
  public String sayHi(@RequestParam("name") String name) {
    return consumerService.sayHiFormClientOne(name);
  }
}
