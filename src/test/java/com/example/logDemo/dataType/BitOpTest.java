package com.example.logDemo.dataType;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class BitOpTest {

  @Test
  public void test() {
    log.info("1 << 0 {}", 1 << 0);
    log.info("1 << 0 {}", 1 << 2);
    log.info("1 << 0 {}", 128 >> 2);
    log.info("1 << 0 {}", -128 >> 2);  //-32
    log.info("1 << 0 {}", -128 >>> 2);  //1073741792 无符号右移
  }
}
