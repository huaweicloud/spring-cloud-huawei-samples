package com.springcloud.eureka.samples;

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
  private DynamicEntity dynamicEntity;

  // consumer service which delegate the implementation to provider service.
  @GetMapping("/sayHello")
  public String sayHello(@RequestParam("name") String name) {
    return restTemplate.getForObject("http://eureka-provider/sayHello?name={1}", String.class, name);
  }

  @GetMapping("/dynamicData")
  public String dynamicData() {
    return "Dynamic Configuration Data : " + dynamicEntity.getDynamicData();
  }
}
