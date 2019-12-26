package com.example.logDemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class SystemTest {

  @Test
  public void testSystem() {
    log.info("getProp {}", System.getProperty("prop"));    //VM Options: -Dprop=xxx
    log.info("getEnv {}", System.getenv("env1"));    //Environment Variables: env1=aaa
  }
}
