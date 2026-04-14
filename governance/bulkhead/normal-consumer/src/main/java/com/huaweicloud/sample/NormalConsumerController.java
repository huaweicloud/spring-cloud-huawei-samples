package com.huaweicloud.sample;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author chengyouling
 * @Date 2025/6/3
 **/
@RestController
public class NormalConsumerController {
  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping("/bulk-service-name")
  public boolean testBulkServiceName() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(10);
    AtomicInteger success = new AtomicInteger();
    AtomicInteger failed = new AtomicInteger();
    for (int i = 0; i < 10; i++) {
      String name = "t-" + i;
      new Thread(name) {
        public void run() {
          try {
            restTemplate.getForObject("http://bulkhead-provider/bulk-service-name", String.class);
            success.getAndIncrement();
          } catch (Exception e) {
            failed.getAndIncrement();
          }
          latch.countDown();
        }
      }.start();
    }
    latch.await(20, TimeUnit.SECONDS);
    return success.get() == 10 && failed.get() == 0;
  }
}
