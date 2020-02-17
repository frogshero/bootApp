package com.example.logDemo.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

@Slf4j
public class ThreadPoolSubmitTest {

  /**
   * ThreadPoolExecutor.execute/submit
   * -> ThreadPoolExecutor.addWorker
   *    -> new Worker //getThreadFactory().newThread(this)创建Thread对象
   *    -> t.start() //线程真正执行开始
   *    -> Worker.run //对worker的控制还是放在ThreadPoolExecutor
   *    -> ThreadPoolExecutor.runWorker
   *        -> while(workQueue.take() != null)  //只要有任务就继续执行，没有则要从workers删除
   *          -> beforeExecute/afterExecutor
   *          -> worker.lock  //一个work同时只能执行一个任务，所以要加锁
   *          -> FutureTask.run (submit)
   *            -> Runnable.run   //用户定义代码
   *            -> FutureTask.set(result) //获取用户定义run方法的值
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
    Integer g = ret.get();
    log.info("result {}", g);
  }
}
