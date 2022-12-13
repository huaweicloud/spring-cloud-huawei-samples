package com.huaweicloud.samples.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer/benchmark")
public class LoadController {

  @Autowired
  private LoadControllerFeign loadControllerFeign;

  @GetMapping("/health")
  public boolean health() {
    return true;
  }

  @PostMapping("/load")
  public String load(@RequestParam(name = "type") int type, @RequestParam(name = "time") int time,
      @RequestBody String input) {
    return loadControllerFeign.load(type, time, input);
  }
}
