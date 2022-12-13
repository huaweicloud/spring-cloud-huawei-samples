package com.huaweicloud.samples.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "basic-provider", path = "/provider/benchmark", contextId = "loadControllerFeign")
public interface LoadControllerFeign {
  @PostMapping("/load")
  String load(@RequestParam(name = "type") int type, @RequestParam(name = "time") int time,
      @RequestBody String input);
}
