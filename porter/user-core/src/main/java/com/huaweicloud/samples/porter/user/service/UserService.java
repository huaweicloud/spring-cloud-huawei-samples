package com.huaweicloud.samples.porter.user.service;

public interface UserService {
  SessionInfo login(String userName, String password);

  SessionInfo getSession(String sessionId);

  String ping(String message);
}
