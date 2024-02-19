package com.huaweicloud.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
  public static void main(String[] args) throws Exception {
    try {
      SpringApplication.run(GatewayApplication.class, args);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
