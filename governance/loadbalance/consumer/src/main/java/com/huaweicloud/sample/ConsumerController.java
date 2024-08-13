package com.huaweicloud.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.config.DynamicPropertyFactory;

@RestController
public class ConsumerController {

  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping("/load-balance")
  public String getOrder(@RequestParam("id") String id) {
    return restTemplate.getForObject("http://load-balance-provider/load-balance?id=" + id, String.class);
  }
}
