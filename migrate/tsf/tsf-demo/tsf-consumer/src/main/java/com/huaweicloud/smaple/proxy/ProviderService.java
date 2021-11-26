package com.huaweicloud.smaple.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("tsf-provider")
public interface ProviderService {

  @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
  String hello(@PathVariable("name") String name);
}
