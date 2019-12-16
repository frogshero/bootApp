package com.example.logDemo.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class CountDownLatchTest {

  @Test
  public void test() throws InterruptedException {
    CountDownLatch countDownLatch = new CountDownLatch(2);

    new Thread(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      countDownLatch.countDown();
    }).start();

//    new Thread(() -> {
//      try {
//        Thread.sleep(100000);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//      countDownLatch.countDown();
//    }).start();

    countDownLatch.await();
  }



}
