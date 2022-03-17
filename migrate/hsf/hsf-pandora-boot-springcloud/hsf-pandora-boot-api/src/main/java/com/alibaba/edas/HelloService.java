package com.alibaba.edas;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;


public interface HelloService {
  @ResponseBody
  @PostMapping(value = "/echo")
    String echo(@RequestParam(value="string") String string);
}
