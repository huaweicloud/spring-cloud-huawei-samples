package com.huaweicloud.samples.porter.user.api;

public class SessionInfo {
  private String sessiondId;

  private String userName;

  private String roleName;

  public String getSessiondId() {
    return sessiondId;
  }

  public void setSessiondId(String sessiondId) {
    this.sessiondId = sessiondId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
}
