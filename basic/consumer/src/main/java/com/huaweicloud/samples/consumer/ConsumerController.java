package com.huaweicloud.samples.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private FeignConsumerService feignConsumerService;

  private ConfigListen configListen;

  @Autowired
  public ConsumerController(ConfigListen configListen) {
    this.configListen = configListen;
  }

  // consumer service which delegate the implementation to provider service.
  @GetMapping("/sayHello")
  public String sayHello(@RequestParam("name") String name) {
    return restTemplate.getForObject("http://basic-provider/sayHello?name={1}", String.class, name);
  }

  @GetMapping("/sayHelloFeign")
  public String sayHelloFeign(@RequestParam("name") String name) {
    return feignConsumerService.sayHelloFeign(name);
  }
}
