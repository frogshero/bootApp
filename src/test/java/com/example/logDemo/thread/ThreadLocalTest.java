package com.example.logDemo.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class ThreadLocalTest {

  /**
   * ThreadLocal.set把真实的值放到Thread.currentThread().ThreadLocalMap这个数组
   * 数组的index由ThreadLocal.threadLocalHashCode
   * ThreadLocal对象是Thread.threadLocals数组的entry的key，怎么避免key冲突？
   * ThreadLocal.nextHashCode是一个静态方法。hashCode来源于静态AtomicInteger变量nextHashCode，
   * 每个hashCode会有一个递增值，所以不会冲突
   */
  ThreadLocal<String> t1 = new ThreadLocal<>();
  ThreadLocal<String> t2 = new ThreadLocal<>();

  @Test
  public void testThreadLocal() throws InterruptedException {
    t1.set(Thread.currentThread().getName());

    int cnt = 10;
    CountDownLatch cdl = new CountDownLatch(cnt);

    for (int i=0; i<10; i++) {
      new Thread(() -> {
        t1.set(Thread.currentThread().getName());
        log.info("{} {}", Thread.currentThread().getName(), t1.get());
        t2.set("xxx");
        cdl.countDown();
      }).start();
    }

    cdl.await();
  }
}
