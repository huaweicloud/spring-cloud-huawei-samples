package com.huaweicloud.samples.porter.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FileApplication {
  private static final Logger LOGGER = LoggerFactory.getLogger(FileApplication.class);

  public static void main(String[] args) {
    try {
      SpringApplication.run(FileApplication.class, args);
    } catch (Throwable e) {
      LOGGER.error("start up failed.", e);
    }
  }
}
