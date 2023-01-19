package com.huaweicloud.samples;

import java.net.URI;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.distribution.CountAtBucket;
import io.micrometer.core.instrument.distribution.HistogramSnapshot;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@Component
public class TestCase {
  private Configuration configuration;

  private RestTemplate restTemplate = new RestTemplate();

  private RestTemplate pooledRestTemplate;

  private AtomicLong index = new AtomicLong(0);

  private AtomicLong success = new AtomicLong(0);

  private AtomicLong timeout = new AtomicLong(0);

  private AtomicLong error = new AtomicLong(0);

  private AtomicLong totalTime = new AtomicLong(0);

  private static MeterRegistry registry = null;

  private static DistributionSummary summary = null;

  @Autowired
  public TestCase(Configuration configuration, RestTemplate pooledRestTemplate) {
    this.configuration = configuration;
    this.pooledRestTemplate = pooledRestTemplate;
  }

  protected void init() {
    registerMetrics();
  }

  public Configuration getConfiguration() {
    return this.configuration;
  }

  private void registerMetrics() {
    if (registry != null) {
      registry.close();
    }
    registry = new SimpleMeterRegistry();
    summary = DistributionSummary
        .builder("response.time")
        .description("response.time")
        .tags("response.time", "test") // optional
        .distributionStatisticExpiry(Duration.ofMinutes(10))
        .serviceLevelObjectives(10D, 20D, 50D, 100D, 200D, 500D, 1000D, 10000D)
        .register(registry);
  }

  protected void run() throws Exception {
    System.out.println("Configurations: threadCount=" + configuration.getThreadCount()
        + ", runCount=" + configuration.getRunCount());
    runTest(configuration.getThreadCount(), configuration.getRunCount(),
        configuration.getData(), configuration.getUrl(),
        (m, u) -> callMethod(m, u));
  }

  private void runTest(int threadCount, int runCount, String message, String url,
      BiConsumer<String, String> function) throws Exception {
    // initialize
    System.out.println("Preparing run =======================================");
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
    System.out.println("Starting run =======================================");

    CountDownLatch latch = new CountDownLatch(threadCount * runCount);
    long begin = System.currentTimeMillis();
    for (int i = 0; i < threadCount; i++) {
      executor.submit(() -> {
        for (int j = 0; j < runCount; j++) {
          long b = System.currentTimeMillis();
          function.accept(message, url);
          totalTime.addAndGet(System.currentTimeMillis() - b);
          summary.record(System.currentTimeMillis() - b);
          latch.countDown();
        }
      });
    }

    latch.await();

    System.out.println("Total time:" + (System.currentTimeMillis() - begin));
    System.out.println("Success count:" + success.get());
    System.out.println("Timeout count:" + timeout.get());
    System.out.println("Error count:" + error.get());
    System.out.println("Success average Latency:" + totalTime.get() / (success.get() + timeout.get() + error.get()));

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
              : Double.valueOf(countAtBucket.count() - countAtBuckets[i - 1].count()).intValue()) + ")|");
        }
        System.out.println(sb);
      }
    }

    executor.shutdownNow();
  }

  private void callMethod(String message, String url) {
    try {
      long begin = System.currentTimeMillis();

      MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
      headers.add("Authorization", configuration.getAuth());
      headers.add("Content-Type", "application/json");
      headers.add("trace-id", "xxxxxx" + index.getAndIncrement());
      RequestEntity<String> requestEntity = new RequestEntity<>(message, headers, HttpMethod.POST,
          new URI(url));

      String result;
      if (configuration.isUsePooledClient()) {
        result = pooledRestTemplate.exchange(requestEntity, String.class).getBody();
      } else {
        result = restTemplate.exchange(requestEntity, String.class).getBody();
      }
      if (!message.equals(result)) {
        error.incrementAndGet();
      } else {
        if (System.currentTimeMillis() - begin >= 5000) {
          timeout.incrementAndGet();
        } else {
          success.incrementAndGet();
        }
      }
    } catch (Throwable e) {
      e.printStackTrace();
      error.incrementAndGet();
    }
  }
}
