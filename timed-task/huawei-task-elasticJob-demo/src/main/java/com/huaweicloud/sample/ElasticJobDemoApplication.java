package com.huaweicloud.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ElasticJobDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(ElasticJobDemoApplication.class, args);
  }
}
