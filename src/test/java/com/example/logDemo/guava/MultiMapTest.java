package com.example.logDemo.guava;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Slf4j
public class MultiMapTest {

  @Test
  public void test() {
    Multimap<String, Integer> mm = LinkedListMultimap.create();
    mm.put("b", 11);
    mm.put("a", 2);
    mm.put("a", 3);
    mm.put("b", 12);
    mm.put("a", 1);

    log.info("{}", Arrays.toString(mm.get("a").toArray()));
  }
}
