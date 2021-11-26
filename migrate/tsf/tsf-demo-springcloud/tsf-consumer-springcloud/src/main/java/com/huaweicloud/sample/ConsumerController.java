package com.huaweicloud.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huaweicloud.sample.proxy.ProviderService;

@RestController
public class ConsumerController {

  @Autowired
  private ProviderService providerService;

  @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
  public String hello(@PathVariable("name") String name){
    return providerService.hello(name);
  }
}
