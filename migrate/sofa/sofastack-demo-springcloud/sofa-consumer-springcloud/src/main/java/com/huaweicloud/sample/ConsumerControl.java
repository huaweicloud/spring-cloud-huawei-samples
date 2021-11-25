package com.huaweicloud.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.huaweicloud.sample.api.HelloService;

@RestController
public class ConsumerControl {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(@RequestParam("name") String name){
        return helloService.hello(name);
    }
}
