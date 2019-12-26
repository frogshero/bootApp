package com.example.logDemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class BootTest {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  public void test() {
    ApiResult ar = testRestTemplate.getForObject("/test/66", ApiResult.class);
    log.info("{}", ar.getCode());
  }
}
