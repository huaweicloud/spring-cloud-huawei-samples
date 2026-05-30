package com.huaweicloud.sample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chengyouling
 * @Date 2025/6/18
 **/
@RestController
public class ProviderAz1Controller {
  @Value("${spring.cloud.servicecomb.discovery.datacenter.region:"
      + "${spring.cloud.nacos.discovery.metadata.region:region}}")
  private String region;

  @Value("${spring.cloud.servicecomb.discovery.datacenter.availableZone:"
      + "${spring.cloud.nacos.discovery.metadata.zone:region}}")
  private String zone;

  @RequestMapping("/zone/both2Az")
  public String zoneAwareBoth2Az() {
    return "provider az1, region: " + region + "; zone: " + zone;
  }

  @RequestMapping("/zone/singleAz")
  public String zoneAwareSingleAz() {
    return "provider az1, region: " + region + "; zone: " + zone;
  }

  @RequestMapping("/zone/denyCross")
  public String zoneAwareDenyCross() {
    return "provider az1, region: " + region + "; zone: " + zone;
  }
}
