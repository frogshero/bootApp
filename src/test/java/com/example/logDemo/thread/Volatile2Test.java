package com.example.logDemo.thread;

import org.junit.Test;

public class Volatile2Test {
  boolean boolValue;
//  private volatile boolean boolValue;

  public void check() {
    if (boolValue == !boolValue)  //不是一个原子操作，两边读取之间可能值已经改变
      System.out.println("WTF!");   //？？？？？？？？？
  }

  public void swap() {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    boolValue = !boolValue;
  }

  /**
   * @Test方法在主线程结束后会自动结束
   */
  @Test
  public void test() throws InterruptedException {
    Thread t2 = new Thread(() -> {
      while (true) {
        check();
      }
    });
    t2.start();

    Thread t1 = new Thread(() -> {
      System.out.println(boolValue);
      while (true) {
        swap();
      }
    });
    t1.start();

    t1.join();
    t2.join();
  }

  /**
   * 只要有线程未结束，就不会结束
   * @param args
   */
  public static void main(String[] args) {
    final Volatile2Test volObj = new Volatile2Test();
    Thread t2 = new Thread(() -> {
        while (true) {
          volObj.check();
        }
    });
    t2.start();

    Thread t1 = new Thread(() -> {
        System.out.println(volObj.boolValue);
        while (true) {
          volObj.swap();
        }
    });
    t1.start();
  }

}
