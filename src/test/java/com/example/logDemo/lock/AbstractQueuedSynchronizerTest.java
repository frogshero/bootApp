package com.example.logDemo.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class AbstractQueuedSynchronizerTest {

  @Test
  public void test() throws InterruptedException {
    Mutex mutex = new Mutex();

    Thread.sleep(1000);
    mutex.lock();
    try {
      log.info("OK~~~");
    } finally {
      mutex.unlock();
    }

    new Thread(()-> {
      mutex.lock();
      try {
        Thread.sleep(1000 * 10);
        log.info("exit~~~");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      finally {
        mutex.unlock();
      }
    }).start();

//    new Thread(()-> {
//      mutex.lock();   //blocked
//      try {
//        Thread.sleep(1000 * 20);
//        log.info("exit~~~");
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      } finally {
////        mutex.unlock();
//      }
//    }).start();

  }
}
