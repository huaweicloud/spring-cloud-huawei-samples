package com.huaweicloud.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author chengyouling
 * @Date 2025/6/18
 **/
@RestController
public class CircuitConsumerController {
  private static final Logger LOGGER = LoggerFactory.getLogger(CircuitConsumerController.class);

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

  @RequestMapping("/circuit/notOpen/errorRate100")
  public String circuitErrorRate100() {
    try {
      return restTemplate.getForObject("http://circuit-provider/circuit/notOpen/errorRate100", String.class);
    } catch (Exception e) {
      LOGGER.warn("circuit errorRate100 failed: " + e.getMessage());
      if (e.getMessage().contains("circuitBreaker is open")) {
        return "circuitBreaker is open!";
      }
      throw e;
    }
  }

  @RequestMapping("/circuit/errorRate50")
  public String circuitErrorRate50() {
    try {
      return restTemplate.getForObject("http://circuit-provider/circuit/errorRate50", String.class);
    } catch (Exception e) {
      LOGGER.warn("circuit errorRate50 failed: " + e.getMessage());
      if (e.getMessage().contains("circuitBreaker is open")) {
        return "circuitBreaker is open!";
      }
      throw e;
    }
  }

  @RequestMapping("/circuit/errorCode500")
  public String circuitErrorCode500(@RequestParam("code") int code) {
    try {
      return restTemplate.getForObject("http://circuit-provider/circuit/errorCode500?code={1}", String.class, code);
    } catch (Exception e) {
      LOGGER.warn("circuit errorCode500 failed: " + e.getMessage());
      if (e.getMessage().contains("circuitBreaker is open")) {
        return "circuitBreaker is open!";
      }
      throw e;
    }
  }

  @RequestMapping("/circuit/headerCode")
  public String circuitHeaderCode() {
    try {
      return restTemplate.getForObject("http://circuit-provider/circuit/headerCode", String.class);
    } catch (Exception e) {
      LOGGER.warn("circuit headerCode failed: " + e.getMessage());
      if (e.getMessage().contains("circuitBreaker is open")) {
        return "circuitBreaker is open!";
      }
      throw e;
    }
  }
}
