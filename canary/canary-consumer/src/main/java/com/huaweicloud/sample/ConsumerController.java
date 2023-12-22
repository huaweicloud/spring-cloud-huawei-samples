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
  static Integer d = 0;

  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping("/canary")
  public String getOrder(@RequestParam("id") String id) {
    String callServiceResult = restTemplate.getForObject("http://canary-provider/provider?id=" + id, String.class);
    return callServiceResult;
  }

  @RequestMapping("/getConfig")
  public String getConfig() {
    String result = DynamicPropertyFactory.getInstance()
        .getStringProperty("servicecomb.routeRule.canary-provider", null, () -> {
          ConsumerController.d++;
        }).get();
    return result + "   " + d;
  }

  @RequestMapping("/canaryFallback")
  public String canaryFallback() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("canary", "fallback");
    HttpEntity<Void> entity = new HttpEntity<>(headers);
    return restTemplate.exchange("http://canary-provider/canaryFallback", HttpMethod.GET, entity,
        String.class).getBody();
  }

  @RequestMapping("/canaryHeaderIsolation")
  public String canaryHeaderIsolation() {
    try {
      return restTemplate.getForObject("http://canary-provider/canaryHeaderIsolation", String.class);
    } catch (Exception e) {
      return "instance isolated";
    }
  }

  @RequestMapping("/canaryHeaderCircuit")
  public String canaryHeaderCircuit() {
    try {
      return restTemplate.getForObject("http://canary-provider/canaryHeaderCircuit", String.class);
    } catch (Exception e) {
      return "circuitBreaker open";
    }
  }
}
