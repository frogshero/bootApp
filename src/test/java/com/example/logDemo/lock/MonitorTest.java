package com.example.logDemo.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class MonitorTest {

  /**
   * wait, notify调用这些监控器方法的前提是拥有锁或进入到synchronized块内。
   * 否则报IllegalMonitorStateException
   * notify通知的是waitSet而不是entryList里的线程
   */
  Object obj = new Object();

  private void aa (String str) throws InterruptedException {
    log.info("{} enter m", str);
    synchronized (obj) {
      log.info("{} enter c", str);
      Thread.sleep(500);
      obj.wait();
    }
    log.info("{} exit m", str);
  }

  @Test
  public void test() throws InterruptedException {
    new Thread(() -> {
      try {
        aa("1");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(() -> {
      try {
        aa("2");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(() -> {
      try {
        Thread.sleep(2000);
//        synchronized (obj) {   //导致IllegalMonitorStateException
          obj.notify();
//        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    Thread.sleep(5000);
  }
}
