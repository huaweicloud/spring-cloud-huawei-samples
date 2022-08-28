package com.huaweicloud.samples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/benchmark/template")
public class BenchmarkTemplateController {
  private final RestTemplate restTemplate;

  @Autowired
  public BenchmarkTemplateController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @PostMapping("/delay/z0")
  public String z0(@RequestBody String input) {
    return restTemplate.postForObject("http://basic-provider/benchmark/delay/z0", input, String.class);
  }

  @PostMapping("/delay/z1")
  public String z1(@RequestBody String input) {
    return restTemplate.postForObject("http://basic-provider/benchmark/delay/z1", input, String.class);
  }

  @PostMapping("/delay/z10")
  public String z10(@RequestBody String input) {
    return restTemplate.postForObject("http://basic-provider/benchmark/delay/z10", input, String.class);
  }

  @PostMapping("/delay/z100")
  public String z100(@RequestBody String input) {
    return restTemplate.postForObject("http://basic-provider/benchmark/delay/z100", input, String.class);
  }
}
