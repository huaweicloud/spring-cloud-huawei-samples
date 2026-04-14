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

  /**
   * 设置睡眠1s模拟实际业务耗时，隔离仓策略设置请求等待时间为0，确保同一时间最大请求TPS为2，其他请求直接错误返回
   *
   * @return
   * @throws InterruptedException
   */
  @RequestMapping("/bulk-rate-limiting")
  public String testBulkRateLimiting() throws InterruptedException {
    Thread.sleep(1000);
    return "provider port ----->" + port + " ,version----->" + version;
  }

  /**
   * 设置睡眠500毫秒模拟实际业务耗时，隔离仓策略设置请求等待时间为2，在等待的时间内请求能够获取资源，进而所有请求正常返回
   *
   * @return
   * @throws InterruptedException
   */
  @RequestMapping("/bulk-no-rate-limiting")
  public String testRetry() throws InterruptedException {
    Thread.sleep(500);
    return "provider port ----->" + port + " ,version----->" + version;
  }

  /**
   * 设置策略只对bulkhead-consumer生效，bulkhead-consumer收到的响应部分异常，对normal-consumer不生效，normal-consumer收到的响应全部正常
   *
   * @return
   * @throws InterruptedException
   */
  @RequestMapping("/bulk-service-name")
  public String testBulkServiceName() throws InterruptedException {
    Thread.sleep(1000);
    return "provider port ----->" + port + " ,version----->" + version;
  }
}
