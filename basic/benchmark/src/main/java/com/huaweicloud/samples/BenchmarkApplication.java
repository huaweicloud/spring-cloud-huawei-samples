package com.huaweicloud.samples;

import java.util.List;
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

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.distribution.CountAtBucket;
import io.micrometer.core.instrument.distribution.HistogramSnapshot;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BenchmarkApplication {
  private static String URL = "http://127.0.0.1:9090";

  private static RestTemplate template = new RestTemplate();

  private static AtomicLong success = new AtomicLong(0);

  private static AtomicLong timeout = new AtomicLong(0);

  private static AtomicLong error = new AtomicLong(0);

  private static AtomicLong totalTime = new AtomicLong(0);

  private static MeterRegistry registry = null;

  private static DistributionSummary summary = null;

  public static void registerMetrics() {
    if (registry != null) {
      registry.close();
    }
    registry = new SimpleMeterRegistry();
    summary = DistributionSummary
        .builder("response.time")
        .description("response.time")
        .tags("response.time", "test") // optional
        .serviceLevelObjectives(10D, 20D, 50D, 100D, 200D, 500D, 1000D)
        .register(registry);
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(BenchmarkApplication.class, args);

    int threadCount = 10;
    int times = 100;

    System.out.println("==================templateZ0========================");
    runTest(threadCount, times, "1234567890", "/benchmark/template/delay/z0",
        BenchmarkApplication::runTest);
    System.out.println("==================templateZ1========================");
    runTest(threadCount, times, "1234567890", "/benchmark/template/delay/z1",
        BenchmarkApplication::runTest);
    System.out.println("==================templateZ10========================");
    runTest(threadCount, times, "1234567890", "/benchmark/template/delay/z10",
        BenchmarkApplication::runTest);
    System.out.println("==================templateZ100========================");
    runTest(threadCount, times, "1234567890", "/benchmark/template/delay/z100",
        BenchmarkApplication::runTest);

    System.out.println("==================feignZ0========================");
    runTest(threadCount, times, "1234567890", "/benchmark/feign/delay/z0",
        BenchmarkApplication::runTest);
    System.out.println("==================feignZ1========================");
    runTest(threadCount, times, "1234567890", "/benchmark/feign/delay/z1",
        BenchmarkApplication::runTest);
    System.out.println("==================feignZ10========================");
    runTest(threadCount, times, "1234567890", "/benchmark/feign/delay/z10",
        BenchmarkApplication::runTest);
    System.out.println("==================feignZ100========================");
    runTest(threadCount, times, "1234567890", "/benchmark/feign/delay/z100",
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
    totalTime.set(0);
    registerMetrics();

    // run
    CountDownLatch latch = new CountDownLatch(threadCount * count);
    long begin = System.currentTimeMillis();
    for (int i = 0; i < threadCount; i++) {
      executor.submit(() -> {
        for (int j = 0; j < count; j++) {
          long b = System.currentTimeMillis();
          function.accept(message, url);
          totalTime.addAndGet(System.currentTimeMillis() - b);
          summary.record(System.currentTimeMillis() - b);
          latch.countDown();
        }
      });
    }

    latch.await();

    System.out.println(System.currentTimeMillis() - begin);
    System.out.println(success.get());
    System.out.println(timeout.get());
    System.out.println(error.get());
    System.out.println(totalTime.get() / success.get());

    List<Meter> meters = registry.getMeters();

    for (Meter meter : meters) {
      if ("response.time".equals(meter.getId().getName())) {
        DistributionSummary distributionSummary = (DistributionSummary) meter;
        HistogramSnapshot histogramSnapshot = distributionSummary.takeSnapshot();
        CountAtBucket[] countAtBuckets = histogramSnapshot.histogramCounts();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < countAtBuckets.length; i++) {
          CountAtBucket countAtBucket = countAtBuckets[i];
          sb.append("|(" + Double.valueOf(countAtBucket.bucket()).intValue()
              + "," + (i == 0 ? Double.valueOf(countAtBucket.count()).intValue()
              : Double.valueOf(countAtBucket.count() - countAtBuckets[i - 1].count()).intValue() + ")|"));
        }
        System.out.println(sb);
      }
    }

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
