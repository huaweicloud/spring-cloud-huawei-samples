package org.apache.servicecomb.samples.porter.user.api;

public interface UserService {
  public SessionInfo login(String userName,
      String password);

  public SessionInfo getSession(String sessionId);

  public String ping(String message);
}
