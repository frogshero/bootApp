package com.example.logDemo.jvm.classloader;

public class Father {

  //实例变量的初始化语句
  int ii;

  //静态变量的初始化语句
  static int fsi = 4;

  //构造代码块在创建对象时被调用，每次创建对象都会调用一次，但是优先于构造函数执行。
  {
    System.out.println("father 1 static block");
  }

  //static代码块语句
  static {
    System.out.println("father 2 static block");
  }

  static Son son = new Son();

  Father() {
    ii = 1;
    System.out.println("father constructor");
  }
}
