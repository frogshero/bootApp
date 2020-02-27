package com.example.logDemo.jvm.classloader;

public class ClassLoadTest {

  //java com.example.logDemo.jvm.classloader.ClassLoadTest 以全限定名读取class文件字节流，在classloader目录下java ClassLoadTest就找不到
  /** Son son = new Son(); =>
    father 1 static block
    father constructor
    son 1 static block
    son 3 constructor
    father 2 static block
    son 2 static block
    father 1 static block
    father constructor
    son 1 static block
    son 3 constructor
    ------end------
  */

  /** Class.forName("com.example.logDemo.jvm.classloader");
     father 2 static block
     father 1 static block
     father constructor
     son 1 static block
     son constructor
     son 2 static block
     ------end------
   */

  /**
   * 静态变量，静态代码块 按代码顺序先后执行
   * 如果有实例则：(构造代码块 -> Contructor) 先父类再子类
   */
  public static void main(String[] args) throws ClassNotFoundException {
//    Son son = new Son();
    Class sonClz = Class.forName("com.example.logDemo.jvm.classloader.Son");
    System.out.println("------end------");
  }
}
