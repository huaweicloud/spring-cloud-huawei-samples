package com.huaweicloud.samples.porter.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableDiscoveryClient
@ImportResource("META-INF/spring/*.bean.xml")
public class UserMain {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(UserMain.class, args);
  }
}
