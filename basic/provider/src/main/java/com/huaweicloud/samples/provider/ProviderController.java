package com.huaweicloud.samples.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
  // a very simple service to echo the request parameter
  @GetMapping("/sayHello")
  public String sayHello(@RequestParam("name") String name) {
    return "Hello " + name;
  }
  @GetMapping("/sayHelloFeign")
  public String sayHelloFeign(@RequestParam("name") String name) {
    return "Hello " + name;
  }
}
