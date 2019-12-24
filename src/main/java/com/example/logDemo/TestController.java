package com.example.logDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
  @Value("${testVal}")
  private String testVal;

  @GetMapping("/test")
  public String test() {
    return testVal;
  }
}
