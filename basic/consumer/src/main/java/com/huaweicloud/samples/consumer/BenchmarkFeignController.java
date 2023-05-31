package com.huaweicloud.samples.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class BenchmarkFeignController {
  private final BenchmarkFeign benchmarkFeign;

  @Autowired
  public BenchmarkFeignController(BenchmarkFeign benchmarkFeign) {
    this.benchmarkFeign = benchmarkFeign;
  }

  @PostMapping("/delay/z0")
  public String z0(@RequestBody String input) {
    return benchmarkFeign.z0(input);
  }

  @PostMapping("/delay/z1")
  public String z1(@RequestBody String input) {
    return benchmarkFeign.z1(input);
  }

  @PostMapping("/delay/z10")
  public String z10(@RequestBody String input) {
    return benchmarkFeign.z10(input);
  }

  @PostMapping("/delay/z100")
  public String z100(@RequestBody String input) {
    return benchmarkFeign.z100(input);
  }
}
