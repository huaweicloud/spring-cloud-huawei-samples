package com.huaweicloud.samples;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BenchmarkApplication {
  private static String URL = "http://127.0.0.1:9090";

  private static RestTemplate template = new RestTemplate();

  private static AtomicLong success = new AtomicLong(0);

  private static AtomicLong timeout = new AtomicLong(0);

  private static AtomicLong error = new AtomicLong(0);

  public static void main(String[] args) throws Exception {
    SpringApplication.run(BenchmarkApplication.class, args);

    System.out.println("==================templateZ0========================");
    runTest(10, 1000, "1234567890", "/benchmark/template/delay/z0",
        BenchmarkApplication::runTest);
    System.out.println("==================templateZ1========================");
    runTest(10, 1000, "1234567890", "/benchmark/template/delay/z1",
        BenchmarkApplication::runTest);
    System.out.println("==================templateZ10========================");
    runTest(10, 1000, "1234567890", "/benchmark/template/delay/z10",
        BenchmarkApplication::runTest);
    System.out.println("==================templateZ100========================");
    runTest(10, 1000, "1234567890", "/benchmark/template/delay/z100",
        BenchmarkApplication::runTest);

    System.out.println("==================feignZ0========================");
    runTest(10, 1000, "1234567890", "/benchmark/feign/delay/z0",
        BenchmarkApplication::runTest);
    System.out.println("==================feignZ1========================");
    runTest(10, 1000, "1234567890", "/benchmark/feign/delay/z1",
        BenchmarkApplication::runTest);
    System.out.println("==================feignZ10========================");
    runTest(10, 1000, "1234567890", "/benchmark/feign/delay/z10",
        BenchmarkApplication::runTest);
    System.out.println("==================feignZ100========================");
    runTest(10, 1000, "1234567890", "/benchmark/feign/delay/z100",
        BenchmarkApplication::runTest);
  }

  private static void runTest(int threadCount, int count, String message, String url,
      BiConsumer<String, String> function) throws Exception {
    // initialize
    ExecutorService executor = Executors.newFixedThreadPool(threadCount, new ThreadFactory() {
      AtomicLong count = new AtomicLong(0);

      @Override
      public Thread newThread(Runnable r) {
        return new Thread(r, "test-thread" + count.getAndIncrement());
      }
    });
    CountDownLatch initLatch = new CountDownLatch(threadCount);
    for (int i = 0; i < threadCount; i++) {
      executor.submit(() -> {
        function.accept(message, url);
        initLatch.countDown();
      });
    }
    initLatch.await();

    success.set(0);
    error.set(0);
    timeout.set(0);

    // run
    CountDownLatch latch = new CountDownLatch(threadCount * count);
    long begin = System.currentTimeMillis();
    for (int i = 0; i < threadCount; i++) {
      executor.submit(() -> {
        for (int j = 0; j < count; j++) {
          function.accept(message, url);
          latch.countDown();
        }
      });
    }

    latch.await();

    System.out.println(System.currentTimeMillis() - begin);
    System.out.println(success.get());
    System.out.println(timeout.get());
    System.out.println(error.get());

    executor.shutdownNow();
  }

  private static void runTest(String message, String url) {
    try {
      String result = template.postForObject(URL + url, message, String.class);
      if (!message.equals(result)) {
        error.incrementAndGet();
      } else {
        success.incrementAndGet();
      }
    } catch (Throwable e) {
      error.incrementAndGet();
    }
  }
}
