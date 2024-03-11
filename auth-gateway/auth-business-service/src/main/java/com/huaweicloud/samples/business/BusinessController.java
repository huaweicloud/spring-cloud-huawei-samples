package com.huaweicloud.samples.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class BusinessController {
  @Value("${server.port}")
  private int port;
  @GetMapping("/authBusiness")
  public String sayHello(@RequestParam("name") String name) {
    return "Hello " + name + "; server port: " + port;
  }
}
