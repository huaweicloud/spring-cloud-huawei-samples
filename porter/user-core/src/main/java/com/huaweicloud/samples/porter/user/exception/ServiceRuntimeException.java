package com.huaweicloud.samples.porter.user.exception;

public class ServiceRuntimeException extends RuntimeException {
  private int statusCode;

  public ServiceRuntimeException(int statusCode, String message) {
    super(message);
    this.statusCode = statusCode;
  }
}
