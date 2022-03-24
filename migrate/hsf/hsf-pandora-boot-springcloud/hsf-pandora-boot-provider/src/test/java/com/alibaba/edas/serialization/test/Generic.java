package com.alibaba.edas.serialization.test;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public class Generic<T> {
  @JsonTypeInfo(use = Id.CLASS)
  private List<T> data;

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }
}
