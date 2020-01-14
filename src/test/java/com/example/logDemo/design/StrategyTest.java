package com.example.logDemo.design;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class StrategyTest {
  private String name = "Test";

  class CompareStrategy implements Comparator<Integer> {
    private String name = "NormalStrategy";
    @Override
    public int compare(Integer o1, Integer o2) {
      log.info(name);
      return o1.compareTo(o2);
    }
  }

  /*
  策略模式
   */
  @Test
  public void test() {
    List<Integer> lists = Lists.newArrayList(8,1,2,3);
    lists.sort(new CompareStrategy());

    lists.sort((a, b) -> {
      log.info(name);
      return a.compareTo(b);
    });
    log.info(Arrays.toString(lists.toArray()));
  }
}
