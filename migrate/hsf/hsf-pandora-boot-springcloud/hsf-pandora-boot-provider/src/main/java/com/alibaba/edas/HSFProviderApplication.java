package com.alibaba.edas;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class HSFProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(HSFProviderApplication.class, args);
    }
}
