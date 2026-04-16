package com.huaweicloud.sample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chengyouling
 * @Date 2025/6/3
 **/
@RestController
public class FaultProviderController {

  @Value("${server.port}")
  private Integer port;

  @Value("${spring.cloud.servicecomb.service.version}")
  private String version;

  @RequestMapping("/fault-exception")
  public String testFaultException() {
    return "provider port ----->" + port + " ,version----->" + version;
  }

  @RequestMapping("/fault-null")
  public String testFaultNull() {
    return "provider port ----->" + port + " ,version----->" + version;
  }

  @RequestMapping("/fault-percent")
  public String testFaultPercent() {
    return "provider port ----->" + port + " ,version----->" + version;
  }

  @RequestMapping("/fault-force")
  public String testFaultForce() {
    return "provider port ----->" + port + " ,version----->" + version;
  }
}
