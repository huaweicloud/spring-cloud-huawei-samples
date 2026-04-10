package com.huaweicloud.sample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chengyouling
 * @Date 2024/8/13
 **/
@RestController
public class ProviderTwoController {

  @Value("${server.port}")
  private Integer port;

  @Value("${spring.cloud.servicecomb.service.version}")
  private String version;

  private int count;

  @RequestMapping("/resetCount")
  public String resetCount() {
    count = 0;
    return "success";
  }

  @RequestMapping("/load-balance")
  public String sayHello(@RequestParam("id") String id) {
    return "provider id-----> " + id + " port ----->" + port + " ,version----->" + version;
  }

  @RequestMapping("/minimum-calls")
  public String testMinimumCalls() {
    return "provider two success!";
  }

  @RequestMapping("/failed-percent")
  public String testFailedPercent(@RequestParam("thresholds") Integer thresholds) {
    return "provider two: " + thresholds;
  }

  @RequestMapping("/slow-call")
  public String testSlowCall(@RequestParam("deferPeriod") Integer deferPeriod) {
    return "provider two: " + deferPeriod;
  }

  @RequestMapping("/force-closed")
  public String testForceClosed() {
    return "provider two success!";
  }

  @RequestMapping("/force-open")
  public String testForceOpen() {
    return "provider two success!";
  }

  @RequestMapping("/force-open-service")
  public String testForceOpenService() {
    return "provider two success!";
  }

  @RequestMapping("/error-code")
  public String testErrorCode() {
    return "provider two success!";
  }
}
