package com.huaweicloud.sample;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import com.huaweicloud.sample.api.HelloService;

@RestController
public class ConsumerControl {

    @DubboReference
    private HelloService helloService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(@RequestParam("name") String name){
        return helloService.hello(name);
    }

}
