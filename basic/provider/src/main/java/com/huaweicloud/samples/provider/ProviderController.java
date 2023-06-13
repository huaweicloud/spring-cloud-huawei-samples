package com.huaweicloud.samples.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ProviderController {

  @Value("${test.name:1}")
  private String testConfig;

  // a very simple service to echo the request parameter
  @GetMapping("/sayHello")
  public String sayHello(@RequestParam("name") String name) {
    return "Hello " + name;
  }

  @GetMapping("/sayHelloFeign")
  public String sayHelloFeign(@RequestParam("name") String name) {
    return "Hello " + name;
  }

  @GetMapping("/testConfig")
  public String testConfig() {
    return "cse config test name: " + testConfig;
  }
}
