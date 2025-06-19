package com.huaweicloud.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author chengyouling
 * @Date 2025/6/3
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class CircuitProviderApplication {
  public static void main(String[] args) {
    SpringApplication.run(CircuitProviderApplication.class, args);
  }
}
