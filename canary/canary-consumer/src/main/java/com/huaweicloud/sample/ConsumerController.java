package com.huaweicloud.sample;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.netflix.config.DynamicPropertyFactory;

@RestController
@RefreshScope
public class ConsumerController {
  static Integer d = 0;

  @Autowired
  private FeignThirdService feignThirdService;

  @Value("${cse.test:}")
  private String test;

  @Autowired
  private RestTemplate restTemplate; //cse调用

  @Autowired
  private RestTemplate restTemplate1 = RestTemplateBuilder.create(); //非CSE调用

  @RequestMapping("/canary")
  public String getOrder(@RequestParam("id") String id) {
    String callServiceResult = restTemplate.getForObject("http://canary-provider/provider?id=" + id, String.class);
    return callServiceResult;
  }

  @RequestMapping("/retry")
  public String retry(@RequestParam("scode") String scode) {
    String callServiceResult = restTemplate.getForObject("http://canary-provider/retry?scode=" + scode, String.class);
    return callServiceResult;
  }

  @RequestMapping("/cir")
  public JSONObject cir(@RequestHeader(value = "times", required = false, defaultValue = "1") int times,
      @RequestHeader(value = "failTimes", required = false, defaultValue = "0") int failTimes,
      @RequestHeader(value = "statusCode", required = false, defaultValue = "0") int scode, HttpServletRequest request) {
    JSONObject result = new JSONObject();
    Map<Integer, String> map = new HashMap<>();
    Map<String, Object> params = new HashMap<>();
    restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
      @Override
      protected boolean hasError(HttpStatus statusCode) {
        return false;
      }
    });
    for (int i = 0; i < times; i++) {
      if (i < failTimes) {
        HttpEntity<ResponseEntity> entity = new HttpEntity<>(null,getHeaders(request));
        ResponseEntity responseEntity = restTemplate.exchange("http://canary-provider/cir?statusCode=" + scode, HttpMethod.GET,
            entity,String.class);
        map.put(i, responseEntity.getBody().toString());
      } else {
        map.put(i,restTemplate.getForObject("http://canary-provider/cir?statusCode=" + 200, String.class,params));
      }
    }
    result.put("times",times);
    result.put("map",map);
    return result;
  }

  @GetMapping("/sayHelloThird")
  public String sayHelloThird(@RequestParam("name") String name) {
    return restTemplate1.getForObject("http://localhost:8087/hello", String.class, name);
  }

  @GetMapping("/sayHelloFeignThird")
  public String sayHelloFeignThird(@RequestParam("name") String name) {
    return feignThirdService.sayHelloFeignThird(name);
  }


  @RequestMapping("/getConfig")
  public String getConfig() {
    return "test--> "+ test;
  }

  public HttpHeaders getHeaders(HttpServletRequest request){
    HttpHeaders headers = new HttpHeaders();
    Enumeration er = request.getHeaderNames();
    while (er.hasMoreElements()){
      String key = er.nextElement().toString();
      headers.set(key,request.getHeader(key));
    }
    return headers;
  }
}
