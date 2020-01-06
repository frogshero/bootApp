package com.example.logDemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateTest {

  @Test
  public void test() {
    LocalDateTime now = LocalDateTime.ofEpochSecond(1577482599L, 0, ZoneOffset.UTC);
    log.info(now.format(DateTimeFormatter.ISO_DATE_TIME));
  }
}
