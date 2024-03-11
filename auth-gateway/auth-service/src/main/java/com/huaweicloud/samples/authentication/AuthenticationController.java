package com.huaweicloud.samples.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class AuthenticationController {
  @Value("${gateway.checkToken:auth2024}")
  private String checkToken;

  @GetMapping("/authentication/authBusiness")
  public boolean sayHello(HttpServletRequest request,
      HttpServletResponse response) {
    String authToken = request.getHeader("x-token");
    System.out.println("==================header token:" + authToken);
    if (StringUtils.isEmpty(authToken) || !checkToken.equals(authToken)) {
      System.out.println("==================403 forbidden");
      response.setStatus(403);
      return false;
    }
    System.out.println("==================200 ok");
    return true;
  }
}
