package com.hauweicloud.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.tsf.annotation.EnableTsf;

@SpringBootApplication
@EnableTsf
public class TSFProviderApplication {
  public static void main(String[] args) {
    SpringApplication.run(TSFProviderApplication.class, args);
  }
}
