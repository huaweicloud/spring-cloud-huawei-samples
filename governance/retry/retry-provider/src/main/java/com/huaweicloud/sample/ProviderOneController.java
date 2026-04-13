package com.huaweicloud.sample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chengyouling
 * @Date 2025/6/3
 **/
@RestController
public class ProviderOneController {

  @Value("${server.port}")
  private Integer port;

  @Value("${spring.cloud.servicecomb.service.version}")
  private String version;

  @RequestMapping("/no-retry")
  public String testNoRetry() {
    return "provider port ----->" + port + " ,version----->" + version;
  }

  @RequestMapping("/test-retry")
  public String testRetry() {
    return "provider port ----->" + port + " ,version----->" + version;
  }

  @RequestMapping("/status-retry")
  public String testStatusRetry(@RequestParam("status") Integer status) {
    return "provider port ----->" + port + " ,version----->" + version;
  }

  @RequestMapping("/retry-service-name")
  public String testRetryServiceName() {
    return "provider port ----->" + port + " ,version----->" + version;
  }

  @RequestMapping("/on-same-retry-one")
  public String testRetryOnSameOne() {
    return "provider port ----->" + port + " ,version----->" + version;
  }

  @RequestMapping("/on-same-retry-two")
  public String testRetryOnSameTwo() {
    return "provider port ----->" + port + " ,version----->" + version;
  }
}
