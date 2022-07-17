package com.huaweicloud.samples.porter.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserEndpoint {
  @Autowired
  private UserService userService;

  @PostMapping(path = "/v1/user/login", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public SessionInfo login(@RequestParam(name = "userName") String userName,
      @RequestParam(name = "password") String password) {
    return userService.login(userName, password);
  }

  @GetMapping(path = "/v1/user/session", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public SessionInfo getSession(@RequestParam(name = "sessionId") String sessionId) {
    return userService.getSession(sessionId);
  }

  @GetMapping(path = "/v1/user/ping", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String ping(@RequestParam(name = "message") String message) {
    return userService.ping(message);
  }
}
