package com.huaweicloud.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

  @Autowired
  private RestTemplate restTemplate;

  /**
   * 实例隔离规则要求最小请求10，provider-one的错误响应达不到10个，错误次数正常统计
   *
   * @return 是否为实例不隔离错误请求数
   */
  @RequestMapping("/low-minimum-calls")
  public boolean testLowMinimumCallsNoIsolation() {
    int failCount = 0;
    for (int i = 0; i < 10; i++) {
      try {
        restTemplate.getForObject("http://isolation-provider/minimum-calls", String.class);
      } catch (Exception e) {
        failCount++;
      }
    }
    // 到达provider-one请求少于10最小请求量，不触发实例隔离，错误值为实际调用请求数
    return failCount == 5;
  }

  /**
   * 实例隔离规则要求最小请求10，provider-one的错误响应达到10个，触发实例隔离，
   * 注意：触发实例隔离后不要连续测试接口，可以等1分钟或者重启consumer后测试
   *
   * @return 是否为实例隔离错误请求数
   */
  @RequestMapping("/up-minimum-calls")
  public boolean testUpMinimumCallsIsolation() {
    int failCount = 0;
    for (int i = 0; i < 30; i++) {
      try {
        restTemplate.getForObject("http://isolation-provider/minimum-calls", String.class);
      } catch (Exception e) {
        failCount++;
      }
    }
    // 到达provider-one的请求大于最小请求量10，触发实例隔离，统计错误值为触发熔断后一个请求，即11
    return failCount == 11;
  }

  /**
   * 实例隔离规则要求最小请求10，provider-one控制10个请求6个错误响应，大于错误率50%触发实例隔离，
   * 注意：触发实例隔离后不要连续测试接口，可以等1分钟或者重启consumer后测试
   *
   * @return 是否为实例隔离错误请求数
   */
  @RequestMapping("/up-failed-percent")
  public boolean testUpFailedPercentIsolation() {
    int failCount = 0;
    for (int i = 0; i < 2; i++) {
      restTemplate.getForObject("http://isolation-provider/resetCount", String.class);
    }
    for (int i = 0; i < 30; i++) {
      try {
        restTemplate.getForObject("http://isolation-provider/failed-percent?thresholds=6", String.class);
      } catch (Exception e) {
        failCount++;
      }
    }
    // 错误率大于50%触发实例隔离，统计错误值为触发熔断后一个请求，即7
    return failCount == 7;
  }

  /**
   * 实例隔离规则要求最小请求10，provider-one控制10个请求3个错误响应，小于错误率50%不触发实例隔离，错误次数为实际错误数
   *
   * @return 是否为实例隔离错误请求数
   */
  @RequestMapping("/low-failed-percent")
  public boolean testLowFailedPercentNoIsolation() {
    int failCount = 0;
    for (int i = 0; i < 2; i++) {
      restTemplate.getForObject("http://isolation-provider/resetCount", String.class);
    }
    for (int i = 0; i < 30; i++) {
      try {
        restTemplate.getForObject("http://isolation-provider/failed-percent?thresholds=3", String.class);
      } catch (Exception e) {
        failCount++;
      }
    }
    // 错误率小于50%不触发实例隔离，统计错误值为到达provider-one响应错误的请求数
    return failCount == 6;
  }

  /**
   * 实例隔离规则要求最小请求10，provider-one控制10个请求3个延迟响应，小于延迟率50%不触发实例隔离，错误次数为实际错误数
   *
   * @return 是否为实例隔离错误请求数
   */
  @RequestMapping("/low-slow-call-percent")
  public boolean testLowSlowCallPercentNoIsolation() {
    int failCount = 0;
    for (int i = 0; i < 2; i++) {
      restTemplate.getForObject("http://isolation-provider/resetCount", String.class);
    }
    for (int i = 0; i < 30; i++) {
      try {
        restTemplate.getForObject("http://isolation-provider/slow-call?deferPeriod=3", String.class);
      } catch (Exception e) {
        failCount++;
      }
    }
    // 延迟率小于50%不触发实例隔离，统计错误值为到达provider-one响应错误的请求数
    return failCount == 6;
  }

  /**
   * 实例隔离规则要求最小请求10，provider-one控制10个请求6个错误响应，大于错误率50%触发实例隔离
   * 注意：触发实例隔离后不要连续测试接口，可以等1分钟或者重启consumer后测试
   *
   * @return 是否为实例隔离错误请求数
   */
  @RequestMapping("/up-slow-call-percent")
  public boolean testUpSlowCallPercentIsolation() {
    int failCount = 0;
    for (int i = 0; i < 2; i++) {
      restTemplate.getForObject("http://isolation-provider/resetCount", String.class);
    }
    for (int i = 0; i < 30; i++) {
      try {
        restTemplate.getForObject("http://isolation-provider/slow-call?deferPeriod=6", String.class);
      } catch (Exception e) {
        failCount++;
      }
    }
    // 到达错误返回实例的请求大于10最小请求量，触发实例隔离，统计错误值为触发熔断后一个请求，即7
    return failCount == 7;
  }

  /**
   * 实例隔离规则要求最小请求10，provider-one的错误响应达到10个，按理触发实例隔离，但强制关闭了策略，不触发实例隔离
   *
   * @return 是否为实例隔离错误请求数
   */
  @RequestMapping("/force-closed")
  public boolean testForceClosedIsolation() {
    int failCount = 0;
    for (int i = 0; i < 30; i++) {
      try {
        restTemplate.getForObject("http://isolation-provider/force-closed", String.class);
      } catch (Exception e) {
        failCount++;
      }
    }
    // 到达provider-one的请求大于最小请求量10，错误率大于50按理触发实例隔离，但强制关闭了策略，隔离不生效，返回错误数15
    return failCount == 15;
  }

  /**
   * 实例隔离策略强制开启后，所有请求均强制错误返回
   *
   * @return 是否为实例隔离错误请求数
   */
  @RequestMapping("/force-open")
  public boolean testForceOpenIsolation() {
    int failCount = 0;
    for (int i = 0; i < 30; i++) {
      try {
        restTemplate.getForObject("http://isolation-provider/force-open", String.class);
      } catch (Exception e) {
        failCount++;
      }
    }
    return failCount == 30;
  }

  /**
   * 实例隔离策略强制开启后，指定的服务(isolation-provider)所有请求均强制错误返回，其他服务正常结果返回
   *
   * @return 是否为实例隔离错误请求数
   */
  @RequestMapping("/force-open-service")
  public boolean testForceOpenServiceNameIsolation() {
    int failCount = 0;
    int successCount = 0;
    for (int i = 0; i < 20; i++) {
      try {
        restTemplate.getForObject("http://isolation-provider/force-open-service", String.class);
        successCount++;
      } catch (Exception e) {
        failCount++;
      }
    }

    for (int i = 0; i < 10; i++) {
      try {
        restTemplate.getForObject("http://no-isolation-provider/force-open-service", String.class);
        successCount++;
      } catch (Exception e) {
        failCount++;
      }
    }
    return failCount == 20 && successCount == 10;
  }

  /**
   * 熔断策略设置500异常熔断策略，provider-one服务达到错误请求后熔断
   *
   * @return 是否为实例隔离错误请求数
   */
  @RequestMapping("/error-code-500")
  public boolean testErrorCodeIsolation() {
    int failCount = 0;
    for (int i = 0; i < 30; i++) {
      try {
        restTemplate.getForObject("http://isolation-provider/error-code?code=500", String.class);
      } catch (Exception e) {
        failCount++;
      }
    }
    return failCount == 11;
  }

  /**
   * 熔断策略设置500异常熔断策略，provider-one服务设置响应错误码404，虽然错误达到策略生效要求，但不进行熔断
   *
   * @return 是否为实例隔离错误请求数
   */
  @RequestMapping("/error-code-404")
  public boolean testErrorCodeNoIsolation() {
    int failCount = 0;
    for (int i = 0; i < 30; i++) {
      try {
        restTemplate.getForObject("http://isolation-provider/error-code?code=404", String.class);
      } catch (Exception e) {
        failCount++;
      }
    }
    return failCount == 15;
  }
}
