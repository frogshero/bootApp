package com.example.logDemo.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ArrayBlockingQueueTest {

  @Test
  public void test() throws InterruptedException {
    ArrayBlockingQueue<String> arr = new ArrayBlockingQueue<>(1);

    new Thread(() -> {
      try {
        TimeUnit.SECONDS.sleep(2);
        arr.put("aa");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    TimeUnit.SECONDS.sleep(1);
    arr.take();
  }
}
