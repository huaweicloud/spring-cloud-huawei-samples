package com.huaweicloud.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author GuoYl123
 * @Date 2019/10/22
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderTwoApplication {
  public static void main(String[] args) {
    SpringApplication.run(ProviderTwoApplication.class, args);
  }
}
