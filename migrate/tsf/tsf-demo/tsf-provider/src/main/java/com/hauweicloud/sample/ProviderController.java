package com.hauweicloud.sample;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

  @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
  public String hello(@PathVariable("name") String name) {
    return "Hello " + name + ", I am from Provider";
  }
}
