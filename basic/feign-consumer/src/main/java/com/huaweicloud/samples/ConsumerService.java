package com.huaweicloud.samples;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @author: HJM
 * @date:2022/3/31 18:22
 */
@FeignClient(value = "basic-provider",configuration = MultipartSupportConfig.class)
public interface ConsumerService {

    @GetMapping("/sayHi")
    String sayHiFormClientOne(@RequestParam("name")String name);

}
