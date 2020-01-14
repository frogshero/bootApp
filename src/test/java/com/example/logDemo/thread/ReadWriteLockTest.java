package com.example.logDemo.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ReadWriteLockTest {

  /**
   * 读写不能并存
   10:47:40.418 [Thread-0] - read start
   10:47:40.418 [Thread-1] - write start
   10:47:42.420 [Thread-0] - read end
   10:47:43.421 [Thread-1] - write end
   */
  @Test
  public void test() throws InterruptedException {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    new Thread(() -> {
      log.info("read start");
      lock.readLock().lock();
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.readLock().unlock();
      }
      log.info("read end");
    }).start();

    new Thread(() -> {
      log.info("write start");
      lock.writeLock().lock();
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.writeLock().unlock();
      }
      log.info("write end");
    }).start();

    TimeUnit.SECONDS.sleep(4);
  }
}
