package com.huaweicloud.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author chengyouling
 * @Date 2025/6/3
 **/
@RestController
public class ConsumerController {

  @Autowired
  private RestTemplate restTemplate;

  /**
   * 当前url为设置重试策略，错误请求不进行重试，结果一般正常一般失败
   *
   * @return boolean
   */
  @RequestMapping("/no-retry")
  public boolean testNoRetry() {
    int failCount = 0;
    int successCount = 0;
    for (int i = 0; i < 10; i++) {
      try {
        restTemplate.getForObject("http://retry-provider/no-retry", String.class);
        successCount++;
      } catch (Exception e) {
        failCount++;
      }
    }
    return failCount == successCount;
  }

  /**
   * 当前url存在对应的重试策略，错误请求进行重试，所有请求正常返回
   *
   * @return boolean
   */
  @RequestMapping("/retry")
  public boolean testRetry() {
    int failCount = 0;
    int successCount = 0;
    for (int i = 0; i < 10; i++) {
      try {
        restTemplate.getForObject("http://retry-provider/test-retry", String.class);
        successCount++;
      } catch (Exception e) {
        failCount++;
      }
    }
    return successCount == 10 && failCount == 0;
  }

  /**
   * 自定义错误码进行重试，500策略中包含，可以实现重试，最终所有的结果为成功；400异常编码策略中未设置，不触发重试，一半成功一般失败
   *
   * @return boolean
   */
  @RequestMapping("/status-retry")
  public boolean testStatusRetry() {
    int count500 = 0;
    int error500 = 0;
    for (int i = 0; i < 10; i++) {
      try {
        restTemplate.getForObject("http://retry-provider/status-retry?status=500", String.class);
        count500++;
      } catch (Exception e) {
        error500++;
      }
    }

    int count400 = 0;
    int error400 = 0;
    for (int i = 0; i < 10; i++) {
      try {
        restTemplate.getForObject("http://retry-provider/status-retry?status=400", String.class);
        count400++;
      } catch (Exception e) {
        error400++;
      }
    }
    return count500 == 10 && error400 == 5 && count400 == 5;
  }

  /**
   * 服务名不是策略指定服务名，重试不生效
   *
   * @return boolean
   */
  @RequestMapping("/retry-service-name")
  public boolean testRetryServiceName() {
    int failCount = 0;
    int successCount = 0;
    for (int i = 0; i < 10; i++) {
      try {
        restTemplate.getForObject("http://retry-provider/retry-service-name", String.class);
        successCount++;
      } catch (Exception e) {
        failCount++;
      }
    }
    return successCount == 5 && failCount == 5;
  }

  /**
   * 设置最大重试1次，指定同实例重试1次，则会优先重试相同实例，致使不能重试到正常结果返回实例，所以错误、正确请求各一半
   *
   * @return boolean
   */
  @RequestMapping("/on-same-retry-one")
  public boolean testRetryOnSameOne() {
    int failCount = 0;
    int successCount = 0;
    for (int i = 0; i < 10; i++) {
      try {
        restTemplate.getForObject("http://retry-provider/on-same-retry-one", String.class);
        successCount++;
      } catch (Exception e) {
        failCount++;
      }
    }
    return successCount == 5 && failCount == 5;
  }

  /**
   * 设置最大重试2次，未指定同实例重试次数，保证能够重试到正常返回结果实例，所以请求正常返回
   *
   * @return boolean
   */
  @RequestMapping("/on-same-retry-two")
  public boolean testRetryOnSameTwo() {
    int failCount = 0;
    int successCount = 0;
    for (int i = 0; i < 10; i++) {
      try {
        restTemplate.getForObject("http://retry-provider/on-same-retry-two", String.class);
        successCount++;
      } catch (Exception e) {
        failCount++;
      }
    }
    return successCount == 10 && failCount == 0;
  }
}
