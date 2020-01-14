package com.example.logDemo.jvm;

public class BigClass {
  /**
   *如果放入eden区超过最大限制则放入old区
   */
  private static byte[] arr = new byte[1]; //[1024 * 1024 * 40];

  /**
   * 字符串常量都放到eden区,
   * 代码如：System.out.println 放到Metaspace区(方法元信息，方法区)
   */
  public void test00() {
    System.out.println("1SLF4J: Found binding in [jar:file:/C:/Users/Dev/.gradle/caches/modules-2/files-2.1/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]");
    System.out.println("2SLF4J: Found binding in [jar:file:/C:/Users/Dev/.gradle/caches/modules-2/files-2.1/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]");
    System.out.println("3SLF4J: Found binding in [jar:file:/C:/Users/Dev/.gradle/caches/modules-2/files-2.1/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]");
  }


}
