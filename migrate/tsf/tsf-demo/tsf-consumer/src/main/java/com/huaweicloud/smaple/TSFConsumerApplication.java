package com.huaweicloud.smaple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.tsf.annotation.EnableTsf;

@SpringBootApplication
@EnableFeignClients
@EnableTsf
public class TSFConsumerApplication {
  public static void main(String[] args) {
    SpringApplication.run(TSFConsumerApplication.class, args);
  }
}
