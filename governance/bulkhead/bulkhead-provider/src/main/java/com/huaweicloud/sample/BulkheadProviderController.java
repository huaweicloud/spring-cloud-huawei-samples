package com.huaweicloud.sample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chengyouling
 * @Date 2025/6/3
 **/
@RestController
public class BulkheadProviderController {

  @Value("${server.port}")
  private Integer port;

  @Value("${spring.cloud.servicecomb.service.version}")
  private String version;

  @RequestMapping("/bulk-rate-limiting")
  public String testBulkRateLimiting() throws InterruptedException {
    Thread.sleep(1000);
    return "provider port ----->" + port + " ,version----->" + version;
  }

  @RequestMapping("/bulk-no-rate-limiting")
  public String testRetry() throws InterruptedException {
    Thread.sleep(500);
    return "provider port ----->" + port + " ,version----->" + version;
  }

  @RequestMapping("/bulk-service-name")
  public String testBulkServiceName() throws InterruptedException {
    Thread.sleep(1000);
    return "provider port ----->" + port + " ,version----->" + version;
  }
}
