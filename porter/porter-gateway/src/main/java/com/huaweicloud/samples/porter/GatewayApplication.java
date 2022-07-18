package com.huaweicloud.samples.porter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
  private static final Logger LOGGER = LoggerFactory.getLogger(GatewayApplication.class);

  public static void main(String[] args) {
    try {
      SpringApplication.run(GatewayApplication.class, args);
    } catch (Exception e) {
      LOGGER.error("start up failed.", e);
    }
  }

  @Bean
  @LoadBalanced
  public WebClient.Builder loadBalancedWebClientBuilder() {
    return WebClient.builder();
  }
}
