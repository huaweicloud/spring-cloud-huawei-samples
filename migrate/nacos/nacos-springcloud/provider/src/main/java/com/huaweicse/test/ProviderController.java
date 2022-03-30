package com.huaweicse.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController implements ProviderService {

    @GetMapping("sayHello")
    public String sayHello(@RequestParam("name") String name) {
        return "hello " + name + ", the demo is for nacos.";
    }

}
