package com.huaweicloud.samples.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "basic-provider", path = "/benchmark", contextId = "benchmarkFeign")
public interface BenchmarkFeign {
  @PostMapping("/delay/z0")
  String z0(@RequestBody String input);

  @PostMapping("/delay/z1")
  String z1(@RequestBody String input);

  @PostMapping("/delay/z10")
  String z10(@RequestBody String input);

  @PostMapping("/delay/z100")
  String z100(@RequestBody String input);
}
