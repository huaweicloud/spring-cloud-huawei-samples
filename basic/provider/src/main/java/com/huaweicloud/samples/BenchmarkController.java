package com.huaweicloud.samples;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/benchmark")
public class BenchmarkController {
  @PostMapping("/delay/z0")
  public String z0(@RequestBody String input) {
    return input;
  }

  @PostMapping("/delay/z1")
  public String z1(@RequestBody String input) {
    try {
      Thread.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return input;
  }

  @PostMapping("/delay/z10")
  public String z10(@RequestBody String input) {
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return input;
  }

  @PostMapping("/delay/z100")
  public String z100(@RequestBody String input) {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return input;
  }
}
