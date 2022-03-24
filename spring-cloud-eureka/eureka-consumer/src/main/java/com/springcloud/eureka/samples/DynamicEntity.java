package com.springcloud.eureka.samples;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("cse.v2.test")
public class DynamicEntity {

  private String dynamicData;

  public String getDynamicData() {
    return dynamicData;
  }

  public void setDynamicData(String dynamicData) {
    this.dynamicData = dynamicData;
  }
}
