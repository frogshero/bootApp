package com.example.logDemo;

import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDateTime;

public class SimpleTest {

  @Test
  public void test() throws ParseException {
    LocalDateTime dt = LocalDateTime.now();
    dt.getDayOfWeek().name();
  }
}
