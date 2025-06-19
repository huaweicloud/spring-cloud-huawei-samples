package com.huaweicloud.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author chengyouling
 * @Date 2025/6/3
 **/
@RestController
public class NormalConsumerController {
  private static final Logger LOGGER = LoggerFactory.getLogger(NormalConsumerController.class);

  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping("/circuit/serviceName")
  public String circuitServiceName() {
    try {
      return restTemplate.getForObject("http://circuit-provider/circuit/serviceName", String.class);
    } catch (Exception e) {
      LOGGER.warn("serviceName circuit failed: " + e.getMessage());
      if (e.getMessage().contains("circuitBreaker is open")) {
        return "circuitBreaker is open!";
      }
      throw e;
    }
  }
}
