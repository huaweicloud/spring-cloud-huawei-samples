package com.alibaba.edas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloServiceImpl implements HelloService {
    @Override
    public String echo(String string) {
        return string;
    }
}
