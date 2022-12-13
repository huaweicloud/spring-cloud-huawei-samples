package com.huaweicloud.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Component
public class BenchmarkApplication {
  public static void main(String[] args) throws Exception {
    ApplicationContext applicationContext = SpringApplication.run(BenchmarkApplication.class, args);
    TestCase test = applicationContext.getBean(TestCase.class);
    Configuration configuration = test.getConfiguration();
    if (configuration.getTestCaseId() == 0) {
      System.out.println("==================templateZ0========================");
      configuration.setUrl("http://127.0.0.1:9090/benchmark/template/delay/z0");
      test.init();
      test.run();

      System.out.println("==================templateZ1========================");
      configuration.setUrl("http://127.0.0.1:9090/benchmark/template/delay/z1");
      test.init();
      test.run();

      System.out.println("==================templateZ10========================");
      configuration.setUrl("http://127.0.0.1:9090/benchmark/template/delay/z10");
      test.init();
      test.run();

      System.out.println("==================templateZ100========================");
      configuration.setUrl("http://127.0.0.1:9090/benchmark/template/delay/z100");
      test.init();
      test.run();

      System.out.println("==================feignZ0========================");
      configuration.setUrl("http://127.0.0.1:9090/benchmark/feign/delay/z0");
      test.init();
      test.run();

      System.out.println("==================feignZ1========================");
      configuration.setUrl("http://127.0.0.1:9090/benchmark/feign/delay/z1");
      test.init();
      test.run();

      System.out.println("==================feignZ10========================");
      configuration.setUrl("http://127.0.0.1:9090/benchmark/feign/delay/z10");
      test.init();
      test.run();

      System.out.println("==================feignZ100========================");
      configuration.setUrl("http://127.0.0.1:9090/benchmark/feign/delay/z100");
      test.init();
      test.run();
    } else {
      test.init();
      test.run();
    }
  }
}
