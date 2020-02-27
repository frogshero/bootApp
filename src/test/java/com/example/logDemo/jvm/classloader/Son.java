package com.example.logDemo.jvm.classloader;

public class Son extends Father {
  private int i = 1;
  private long l = 2L;
  static int ssi = 3;

  {
    System.out.println("son 1 static block");
  }

  static {
    System.out.println("son 2 static block");
  }

  Son() {
    l = 3L;
    System.out.println("son constructor");
  }
}
