package com.huaweicloud.sample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author chengyouling
 * @Date 2025/6/3
 **/
@RestController
public class ProviderTwoController {

  @Value("${server.port}")
  private Integer port;

  @Value("${spring.cloud.servicecomb.service.version}")
  private String version;

  @RequestMapping("/no-retry")
  public String testNoRetry(HttpServletResponse response) {
    response.setStatus(502);
    return "provider port ----->" + port + " ,version----->" + version;
  }

  /**
   * DefaultHttpRequestRetryStrategy定义的retriableCodes中包含SC_TOO_MANY_REQUESTS(429)、SC_SERVICE_UNAVAILABLE(503)异常编码，最大重试次数1次，间隔1s
   * 当ExecChainHandler使用了HttpRequestRetryExec执行调用请求时，响应码设置为503或429时，就会默认触发一次重试.
   * 叠加resilience4j的重试，请求到响应503/429实例时，消耗的时间要加上HttpRequestRetryExec的重试1秒时间
   *
   * @param response
   * @return
   */
  @RequestMapping("/test-retry")
  public String testRetry(HttpServletResponse response) {
    response.setStatus(502);
    return "provider port ----->" + port + " ,version----->" + version;
  }

  @RequestMapping("/status-retry")
  public String testStatusRetry(@RequestParam("status") Integer status, HttpServletResponse response) {
    response.setStatus(status);
    return "provider port ----->" + port + " ,version----->" + version;
  }

  @RequestMapping("/retry-service-name")
  public String testRetryServiceName(HttpServletResponse response) {
    response.setStatus(502);
    return "provider port ----->" + port + " ,version----->" + version;
  }

  @RequestMapping("/on-same-retry-one")
  public String testRetryOnSameOne(HttpServletResponse response) {
    response.setStatus(502);
    return "provider port ----->" + port + " ,version----->" + version;
  }

  @RequestMapping("/on-same-retry-two")
  public String testRetryOnSameTwo(HttpServletResponse response) {
    response.setStatus(502);
    return "provider port ----->" + port + " ,version----->" + version;
  }
}
