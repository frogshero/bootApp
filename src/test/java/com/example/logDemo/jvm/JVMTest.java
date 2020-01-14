package com.example.logDemo.jvm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.management.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class JVMTest {

  /**
   * 查看java进程 jps -l | findStr junit
   * jstat -gccapacity 7280
   *
   * 大对象直接分配到Old区，否则PS Eden Space
   *
   * 使用java -XX:+PrintFlagsInitial命令查看本机的初始化参数
   * uintx MetaspaceSize                             = 21810376                            {pd product}
   *
   * java -XX:+PrintCommandLineFlags -version //打印HotSpotVM 采用的自动优化参数
   *
   * -Xloggc:gc.log
   * -XX:+PrintGCTimeStamps
   * -XX:+PrintGCDetails
   * -XX:+UseG1GC
   * -XX:+UseStringDeduplication
   * -XX:+PrintStringDeduplicationStatistics 开启字符串去重
   */
  @Test
  public void test() throws Exception {
    Runtime runtime = Runtime.getRuntime();
    log.info("Total Memory: {} {} {}", runtime.totalMemory(), runtime.maxMemory(), runtime.freeMemory());

    //log.info("----------------------------get class before {}", Runtime.getRuntime().totalMemory());
    prtPool();

    Thread.sleep(1000);
    Class clz = Class.forName("com.example.logDemo.jvm.BigClass");
    //log.info("----------------------------get class after {}", Runtime.getRuntime().totalMemory());
    prtPool();

    Thread.sleep(1000);
    clz.newInstance();
    //log.info("----------------------------get instance after {}", Runtime.getRuntime().totalMemory());
    prtPool();

    Thread.sleep(1000);
    prtPool();
  }

  //Java Management Extensions
  private void prtPool() {
//    MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
    List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
    for (MemoryPoolMXBean poolMXBean : pools) {
      MemoryUsage usage = poolMXBean.getUsage();

      System.out.println(poolMXBean.getName() + "\t" + usage.getUsed() + "\t" + usage.getCommitted());
    }
    System.out.println(" \t \t ");
  }

  @Test
  public void testVariable() {
    int i = 0;
    log.info("i: {}", i++);  //0 局部变量表值先压入栈，局部变量表值加1，取出栈顶值使用。这i同时存在2个值，一个在栈一个在局部变量表~~
    log.info("i: {}", ++i);  //2 局部变量表值+1，入栈，取出栈顶值使用。i只有1个值
  }

  private static final LinkedList<String> LOTS_OF_STRINGS = new LinkedList<>();

  /**
   * -ea
   * -Xmx128m
   * -XX:+UseG1GC
   * -XX:+UseStringDeduplication
   * -XX:+PrintStringDeduplicationStatistics
   * G1: 正常可以跑15，加-XX:+UseStringDeduplication可以跑24，用intern可以跑54
   * Parallel: 13, 13, 42
   */
  @Test
  public void testeIntern() throws InterruptedException {
    int iteration = 0;
    while (true) {
      for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 1000; j++) {
          LOTS_OF_STRINGS.add(("String " + j).intern());
        }
      }
      iteration++;
      System.out.println("Survived Iteration: " + iteration);
      Thread.sleep(100);
    }
//      "asdf".intern();
  }

  @Test
  public void produceMethod() throws IOException {
    List<String> sb = new ArrayList<>();
    for (int j=0; j<10; j++) {
      sb.add("public void test" + j + "() {");
      for (int i = 0; i < 1000; i++) {
        sb.add("System.out.println(\"SLF4J: Found binding in [jar:file:/C:/Users/Dev/.gradle/caches/modules-2/files-2.1/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]\");");
      }
      sb.add("}");
    }
    BufferedWriter bw = Files.newBufferedWriter(Paths.get("d:/dd.txt"));
    for (String s : sb) {
      bw.write(s + '\n');
    }
    bw.flush();
  }
}
