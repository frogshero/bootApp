package com.example.logDemo.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

@Slf4j
public class CuratorTest {

  @Test
  public void test() throws Exception {
    RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);

    CuratorFramework cf = CuratorFrameworkFactory.builder()
            .connectString("localhost:2181")
            .sessionTimeoutMs(3000)
            .retryPolicy(retryPolicy)
            .namespace("test")
            .build();
    cf.start();
    log.info("state: {}", cf.getState());

    cf.create().forPath("/curator", "curator1".getBytes());
    cf.close();
  }
}
