package com.huaweicloud.sample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chengyouling
 * @Date 2024/8/13
 **/
@RestController
public class ProviderThreeController {

  @Value("${server.port}")
  private Integer port;

  @Value("${spring.cloud.servicecomb.service.version}")
  private String version;

  @RequestMapping("/force-open-service")
  public String testForceOpenService() {
    return "provider three success!";
  }
}
