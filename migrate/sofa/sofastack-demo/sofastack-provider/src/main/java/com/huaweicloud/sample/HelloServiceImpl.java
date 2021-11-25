package com.huaweicloud.sample;


import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.huaweicloud.sample.api.HelloService;

import org.springframework.stereotype.Service;

@Service
@SofaService(interfaceType = HelloService.class, uniqueId = "${service.unique.id}", bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello " + name + ", I am form Provider";
    }
}
