package com.example.logDemo.dataType;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.Test;

@Slf4j
public class StringTest {

  @Test
  public void test() {
    log.info(RandomString.hashOf(10000));
  }

}
