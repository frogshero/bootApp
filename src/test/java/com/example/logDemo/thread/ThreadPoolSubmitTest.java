package com.example.logDemo.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

@Slf4j
public class ThreadPoolSubmitTest {

  /**
   * ThreadPoolExecutor.execute/submit
   * -> addWorker
   * -> new Worker //getThreadFactory().newThread(this)创建Thread对象
   * -> Worker.run
   * -> runWorker
   * -> FutureTask.run (submit) //用Runnable是框架代码有机会把代码放在用户定义代码前后执行，如获取用户定义run方法的值
   * -> Runnable.run
   */
  @Test
  public void testSubmit() throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    log.info("start callable");
    Future<Integer> ret = executorService.submit(() -> {
      TimeUnit.SECONDS.sleep(1);
      return 11;
    });
    //在get里检查状态并休眠
    log.info("result {}", ret.get());
  }
}
