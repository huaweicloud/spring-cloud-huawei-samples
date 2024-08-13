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
public class ProviderThreeController {

  @Value("${server.port}")
  private Integer port;

  @Value("${spring.cloud.servicecomb.discovery.version}")
  private String version;

  @RequestMapping("/load-balance")
  public String sayHello(@RequestParam("id") String id) {
    return "provider id-----> " + id + " port ----->" + port + " ,version----->" + version;
  }
}
