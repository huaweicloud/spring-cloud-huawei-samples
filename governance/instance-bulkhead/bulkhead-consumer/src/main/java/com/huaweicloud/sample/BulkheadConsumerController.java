package com.huaweicloud.sample;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @Author chengyouling
 * @Date 2025/6/3
 **/
@RestController
public class BulkheadConsumerController {

  @Autowired
  private RestTemplate restTemplate;

  /**
   * 服务端接口设置睡眠1s模拟实际业务耗时，隔离仓策略设置请求等待时间为0，确保同一时间请求一个实例的TPS为2，其他请求直接错误返回
   *
   * @return
   * @throws InterruptedException
   */
  @RequestMapping("/bulk-rate-limiting")
  public String testBulkRateLimiting() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(10);
    AtomicBoolean failed = new AtomicBoolean(false);
    for (int i = 0; i < 10; i++) {
      String name = "t-" + i;
      new Thread(name) {
        public void run() {
          try {
            restTemplate.getForObject("http://bulkhead-provider/bulk-rate-limiting", String.class);
          } catch (Exception e) {
            failed.set(true);
          }
          latch.countDown();
        }
      }.start();
    }
    latch.await(20, TimeUnit.SECONDS);
    return failed.get() ? "请求结果符合策略要求" : "请求结果不符合策略要求";
  }

  /**
   * 服务端设置睡眠500毫秒模拟实际业务耗时，隔离仓策略设置请求等待时间为2，在等待的时间内请求能够获取资源，进而所有请求正常返回
   *
   * @return
   * @throws InterruptedException
   */
  @RequestMapping("/bulk-no-rate-limiting")
  public String testBulkNoRateLimiting() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(10);
    AtomicInteger success = new AtomicInteger();
    AtomicInteger failed = new AtomicInteger();
    for (int i = 0; i < 10; i++) {
      String name = "t-" + i;
      new Thread(name) {
        public void run() {
          try {
            restTemplate.getForObject("http://bulkhead-provider/bulk-no-rate-limiting", String.class);
            success.getAndIncrement();
          } catch (Exception e) {
            failed.getAndIncrement();
          }
          latch.countDown();
        }
      }.start();
    }
    latch.await(20, TimeUnit.SECONDS);
    return (success.get() == 10 && failed.get() == 0) ? "请求结果符合策略要求" : "请求结果不符合策略要求";
  }

  /**
   * 设置策略只对bulkhead-provider生效，调用bulkhead-provider请求部分异常，对normal-provider不生效，normal-provider正常处理所有请求
   *
   * @return
   * @throws InterruptedException
   */
  @RequestMapping("/bulk-service-name")
  public String testBulkServiceName() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(20);
    AtomicBoolean bulkFailed = new AtomicBoolean(false);
    AtomicBoolean normalFailed = new AtomicBoolean(false);
    for (int i = 0; i < 10; i++) {
      String name = "t-" + i;
      new Thread(name) {
        public void run() {
          try {
            restTemplate.getForObject("http://bulkhead-provider/bulk-service-name", String.class);
          } catch (Exception e) {
            bulkFailed.set(true);
          }
          latch.countDown();
        }
      }.start();
    }
    for (int i = 0; i < 10; i++) {
      String name = "t-" + i;
      new Thread(name) {
        public void run() {
          try {
            restTemplate.getForObject("http://normal-provider/bulk-service-name", String.class);
          } catch (Exception e) {
            normalFailed.set(true);
          }
          latch.countDown();
        }
      }.start();
    }
    latch.await(30, TimeUnit.SECONDS);
    return bulkFailed.get() && !normalFailed.get() ? "请求结果符合策略要求" : "请求结果不符合策略要求";
  }
}
