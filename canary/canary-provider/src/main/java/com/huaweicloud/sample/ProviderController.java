package com.huaweicloud.sample;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author GuoYl123
 * @Date 2019/10/22
 **/
@RestController
public class ProviderController {

  @Value("${server.port:}")
  private Integer port;

  @RequestMapping("/provider")
  public String sayHello(@RequestParam("id") String id) {
    return "provider ---> " + id + " port -->" + port;
  }

  @RequestMapping("/retry")
  public String retry(HttpServletResponse response, @RequestParam("scode") int scode) {
    response.setStatus(scode);
    return "retry";
  }

  @RequestMapping("/cir")
  public String cir(HttpServletResponse response, @RequestParam("statusCode") int statusCode) {
    if (statusCode == 200) {
      return "ok";
    } else {
      response.setStatus(statusCode);
      return "err code is" + statusCode;
    }
  }
}
