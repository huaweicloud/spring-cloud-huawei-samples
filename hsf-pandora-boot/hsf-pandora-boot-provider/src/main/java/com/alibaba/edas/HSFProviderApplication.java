package com.alibaba.edas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HSFProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(HSFProviderApplication.class, args);
    }
}
