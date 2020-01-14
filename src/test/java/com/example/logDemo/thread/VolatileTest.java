package com.example.logDemo.thread;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class VolatileTest {

  private Integer cnt = 0;
//  private volatile Integer cnt = 0;

  @Test
  public void test() throws InterruptedException {
    CountDownLatch cdl = new CountDownLatch(1);
    int loopCnt = 50;

    Thread t1 = new Thread(() -> {
      List<Integer> lists = new ArrayList<>(1000);
      String name = Thread.currentThread().getName();
      try {
        cdl.await();
        for (int i = 0; i < loopCnt; i++) {
          cnt++;
//          log.info("update {}", cnt);
          lists.add(cnt);
        }
        log.info("{}, {}", lists.size(), StringUtils.collectionToDelimitedString(lists, ","));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread t2 = new Thread(() -> {
      List<Integer> lists = new ArrayList<>(1000);
      try {
        cdl.await();
        for (int i = 0; i < loopCnt; i++) {
//          log.info("read {}", cnt);
          lists.add(cnt);
        }
        log.info("{}, {}", lists.size(), StringUtils.collectionToDelimitedString(lists, ","));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    t1.start();
    t2.start();
    Thread.sleep(1000);

    cdl.countDown();
    t1.join();
    t2.join();

  }
}
