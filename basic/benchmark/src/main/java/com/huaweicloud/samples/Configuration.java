package com.huaweicloud.samples;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "test")
@Component
public class Configuration {
  private int runCount = 1000;

  private int threadCount = 100;

  private String url;

  private boolean usePooledClient = false;

  private String data = "ABCDEFGHIJ";

  private String auth = null;

  private int testCaseId = 0;

  public int getRunCount() {
    return runCount;
  }

  public void setRunCount(int runCount) {
    this.runCount = runCount;
  }

  public int getThreadCount() {
    return threadCount;
  }

  public void setThreadCount(int threadCount) {
    this.threadCount = threadCount;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isUsePooledClient() {
    return usePooledClient;
  }

  public void setUsePooledClient(boolean usePooledClient) {
    this.usePooledClient = usePooledClient;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public int getTestCaseId() {
    return testCaseId;
  }

  public void setTestCaseId(int testCaseId) {
    this.testCaseId = testCaseId;
  }

  public String getAuth() {
    return auth;
  }

  public void setAuth(String auth) {
    this.auth = auth;
  }
}
