package com.huaweicloud.sample;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author chengyouling
 * @Date 2025/6/3
 **/
@RestController
public class FaultConsumerController {

  @Autowired
  private RestTemplate restTemplate;

  /**
   * 降级策略设置降级请求百分比100，所有请求直接异常返回，错误率100%
   *
   * @return result
   */
  @RequestMapping("/fault-exception")
  public String testFaultException() {
    int success = 0;
    int failed = 0;
    for (int i = 0; i < 10; i++) {
      try {
        restTemplate.getForObject("http://fault-provider/fault-exception", String.class);
        success++;
      } catch (Exception e) {
        if (e.getMessage().contains("aborted by fault inject")) {
          failed++;
        }
      }
    }
    return failed == 10 && success == 0 ? "请求结果符合策略要求" : "请求结果不符合策略要求";
  }

  /**
   * 降级策略设置降级请求百分比100，所有请求直接返回null
   *
   * @return result
   */
  @RequestMapping("/fault-null")
  public String testFaultNull() {
    int nullCount = 0;
    int failed = 0;
    for (int i = 0; i < 10; i++) {
      try {
        String result = restTemplate.getForObject("http://fault-provider/fault-null", String.class);
        if (result == null) {
          nullCount++;
        }
      } catch (Exception e) {
        failed++;
      }
    }
    return failed == 0 && nullCount == 10 ? "请求结果符合策略要求" : "请求结果不符合策略要求";
  }

  /**
   * 降级策略设置降级请求百分比50，一半请求正常返回，一半降级异常返回
   *
   * @return result
   */
  @RequestMapping("/fault-percent")
  public String testFaultPercent() {
    int success = 0;
    int failed = 0;
    for (int i = 0; i < 10; i++) {
      try {
        restTemplate.getForObject("http://fault-provider/fault-percent", String.class);
        success++;
      } catch (Exception e) {
        if (e.getMessage().contains("aborted by fault inject")) {
          failed++;
        }
      }
    }
    return failed == 5 && success == 5 ? "请求结果符合策略要求" : "请求结果不符合策略要求";
  }

  /**
   * 降级策略设置降级请求百分比100，指定对fault-provider服务生效，所有请求到fault-provider的所有请求降级异常返回，
   * 请求到normal-provider的所有请求正常返回
   *
   * @return result
   */
  @RequestMapping("/fault-service-name")
  public String testFaultServiceName() {
    int faultSuccess = 0;
    int faultFailed = 0;
    for (int i = 0; i < 10; i++) {
      try {
        restTemplate.getForObject("http://fault-provider/fault-service-name", String.class);
        faultSuccess++;
      } catch (Exception e) {
        if (e.getMessage().contains("aborted by fault inject")) {
          faultFailed++;
        }
      }
    }

    int normalSuccess = 0;
    int normalFailed = 0;
    for (int i = 0; i < 10; i++) {
      try {
        restTemplate.getForObject("http://normal-provider/fault-service-name", String.class);
        normalSuccess++;
      } catch (Exception e) {
        if (e.getMessage().contains("aborted by fault inject")) {
          normalFailed++;
        }
      }
    }

    return faultFailed == 10 && normalSuccess == 10
        && normalFailed == 0 && faultSuccess == 0 ? "请求结果符合策略要求" : "请求结果不符合策略要求";
  }

  /**
   * 降级策略强制关闭开关打开，所有请求不作降级
   *
   * @return result
   */
  @RequestMapping("/fault-force")
  public String testFaultForce() {
    int success = 0;
    int failed = 0;
    for (int i = 0; i < 10; i++) {
      try {
        restTemplate.getForObject("http://fault-provider/fault-force", String.class);
        success++;
      } catch (Exception e) {
        if (e.getMessage().contains("aborted by fault inject")) {
          failed++;
        }
      }
    }
    return failed == 0 && success == 10 ? "请求结果符合策略要求" : "请求结果不符合策略要求";
  }
}
