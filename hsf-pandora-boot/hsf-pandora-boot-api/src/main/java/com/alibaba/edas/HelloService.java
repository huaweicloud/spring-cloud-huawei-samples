package com.alibaba.edas;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public interface HelloService {
    @PostMapping(value = "/echo")
    @ResponseBody
    String echo(@RequestParam("name") String string);
}
