package com.huaweicloud.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author chengyouling
 * @Date 2026/5/18
 **/
@RestController
public class ConsumerAz1Controller {
  @Autowired
  private RestTemplate restTemplate;

  private final String az1Info = "provider az1, region: region1; zone: az1";

  /**
   * 同时启动az1、az2的provider服务，开启亲和，返回az1的provider信息
   *
   * @return 是否符合结果
   */
  @RequestMapping("/zone/both2Az")
  public boolean zoneAwareBoth2Az() {
    String result = restTemplate.getForObject("http://provider-zone/zone/both2Az", String.class);
    return az1Info.equals(result);
  }

  /**
   * 当仅启动az2的provider且未开启强制亲和时，返回az2的provider信息
   *
   * @return 是否符合结果
   */
  @RequestMapping("/zone/singleAz")
  public boolean zoneAwareSingleAz() {
    String result = restTemplate.getForObject("http://provider-zone/zone/singleAz", String.class);
    String az1Info = "provider az2, region: region2; zone: az2";
    return az1Info.equals(result);
  }

  /**
   * 同时启动az1、az2的provider服务，开启强制亲和(打开application.yaml中的denyCrossZoneLoadBalancing配置后重启服务)，返回az1的provider信息
   *
   * @return 是否符合结果
   */
  @RequestMapping("/zone/denyCross/bothAz")
  public boolean zoneAwareDenyCrossWithProviderAz1() {
    String result = restTemplate.getForObject("http://provider-zone/zone/denyCross", String.class);
    return az1Info.equals(result);
  }

  /**
   * 当仅启动az2的provider且开启强制亲和时(打开application.yaml中的denyCrossZoneLoadBalancing配置后重启服务)，返回错误信息
   *
   * @return 是否符合结果
   */
  @RequestMapping("/zone/denyCross")
  public boolean zoneAwareDenyCrossNoProviderAz1() {
    String errorMessage = "";
    try {
      restTemplate.getForObject("http://provider-zone/zone/denyCross", String.class);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    return errorMessage.contains("No instances available");
  }
}
