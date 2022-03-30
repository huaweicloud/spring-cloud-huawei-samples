package com.huaweicse.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

  @Autowired
  private ProviderService providerService;

  @GetMapping("sayHello")
  public String sayHello(@RequestParam("name") String name) {
    return providerService.sayHello(name);
  }
}
