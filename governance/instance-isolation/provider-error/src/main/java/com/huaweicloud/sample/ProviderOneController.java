package com.huaweicloud.sample;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author chengyouling
 * @Date 2026/3/13
 **/
@RestController
public class ProviderOneController {
  private int count;

  @RequestMapping("/resetCount")
  public String resetCount() {
    count = 0;
    return "success";
  }

  @RequestMapping("/minimum-calls")
  public String testMinimumCalls(HttpServletResponse response) {
    response.setStatus(503);
    return "provider one error!";
  }

  @RequestMapping("/failed-percent")
  public String testFailedPercent(HttpServletResponse response, @RequestParam("thresholds") Integer thresholds) {
    count++;
    if (count <= thresholds) {
      response.setStatus(503);
    }
    if (count == 10) {
     count = 0;
    }
    return "provider one: " + count;
  }

  @RequestMapping("/slow-call")
  public String testSlowCall(HttpServletResponse response, @RequestParam("deferPeriod") Integer deferPeriod)
      throws InterruptedException {
    count++;
    if (count <= deferPeriod) {
      response.setStatus(503);
      Thread.sleep(3000);
    }
    if (count == 10) {
      count = 0;
    }
    return "provider one: " + count;
  }

  @RequestMapping("/force-closed")
  public String testForceClosed(HttpServletResponse response) {
    response.setStatus(503);
    return "provider one error!";
  }

  @RequestMapping("/force-open")
  public String testForceOpen(HttpServletResponse response) {
    response.setStatus(503);
    return "provider one error!";
  }

  @RequestMapping("/force-open-service")
  public String testForceOpenService(HttpServletResponse response) {
    response.setStatus(503);
    return "provider one error!";
  }

  @RequestMapping("/error-code")
  public String testErrorCode(HttpServletResponse response, @RequestParam("code") Integer code) {
    response.setStatus(code);
    return "provider two success!";
  }
}
