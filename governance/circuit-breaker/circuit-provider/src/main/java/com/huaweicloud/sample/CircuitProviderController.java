package com.huaweicloud.sample;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author chengyouling
 * @Date 2025/6/18
 **/
@RestController
public class CircuitProviderController {
  private int count = 1;

  @RequestMapping("/circuit/serviceName")
  public String circuitServiceName(HttpServletResponse response) {
    if (count % 2 == 0) {
      response.setStatus(502);
    }
    count++;
    return "OK";
  }

  @RequestMapping("/circuit/notOpen/errorRate100")
  public String circuitNotOpenErrorRate100(HttpServletResponse response) {
    if (count % 5 != 0) {
      response.setStatus(502);
    }
    count++;
    return "OK";
  }

  @RequestMapping("/circuit/errorRate50")
  public String circuitErrorRate50(HttpServletResponse response) {
    if (count % 2 == 0) {
      response.setStatus(502);
    }
    count++;
    return "OK";
  }

  @RequestMapping("/circuit/errorCode500")
  public String circuitErrorCode500(@RequestParam("code") int code, HttpServletResponse response) {
    response.setStatus(code);
    return "OK";
  }

  @RequestMapping("/circuit/headerCode")
  public String circuitHeaderCode(HttpServletResponse response) {
    response.addHeader("X-HTTP-STATUS-CODE", "502");
    return "OK";
  }
}
