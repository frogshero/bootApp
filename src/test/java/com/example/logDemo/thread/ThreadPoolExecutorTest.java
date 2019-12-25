package com.example.logDemo.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExecutorTest {

  class MyExecutor extends ThreadPoolExecutor {
    public MyExecutor() {
      super(2, 10, 10, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
    }
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
      log.info("beforeExecute");
    }
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
      log.info("afterExecute");
    }
  }

  @Test
  public void testExecutor() throws InterruptedException {
    ThreadPoolExecutor tpe = new MyExecutor();
    Runnable r = () -> {
      try {
        Thread.sleep(1000*10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };

    tpe.execute(r);
    tpe.execute(r);
    //core=2
    tpe.execute(r);

    while (tpe.getActiveCount() > 0) {
      Thread.sleep(1000);
    }

    tpe.shutdown();
  }
}
