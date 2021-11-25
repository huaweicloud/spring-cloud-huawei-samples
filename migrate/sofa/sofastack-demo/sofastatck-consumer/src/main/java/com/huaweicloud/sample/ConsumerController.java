package com.huaweicloud.sample;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.huaweicloud.sample.api.HelloService;

import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerController {

  @SofaReference(interfaceType = HelloService.class, uniqueId = "${service.unique.id}", binding = @SofaReferenceBinding(bindingType = "bolt"))
  private HelloService helloService;

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String hello(@RequestParam("name") String name) {
    return helloService.hello(name);
  }
}
