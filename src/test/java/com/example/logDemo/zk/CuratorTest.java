package com.example.logDemo.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.Test;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CuratorTest {

  static RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);

  static CuratorFramework cf = CuratorFrameworkFactory.builder()
          .connectString("localhost:2181")
          .sessionTimeoutMs(3000)
          .retryPolicy(retryPolicy)
          .namespace("test2")
          .build();

  static {
    cf.start();
  }

  @Test
  public void test() throws Exception {
    log.info("state: {}", cf.getState());

    //创建临时结点：连接一失去，临时结点就会被删除
    cf.create().withMode(CreateMode.EPHEMERAL).forPath("/ephemeral", "ephemeral1".getBytes());
//    if (cf.checkExists().forPath("/eee") != null) {
//      cf.delete().forPath("/eee");
//    }

    String path = "/curator/ffa";
    if (cf.checkExists().forPath(path) != null) {
      //自动先删子结点再删父节点
      cf.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
    }
    //如果没有父节点，自动创建
    cf.create().creatingParentsIfNeeded().forPath(path, "ffa".getBytes());

    //log.info("data {}", new String(cf.getData().forPath("/test/ephemeral"))); //NoNodeException: KeeperErrorCode = NoNode for /test/test/ephemeral
//    cf.getChildren().forPath()
    TimeUnit.SECONDS.sleep(10);
    cf.close();
  }

  @Test
  public void testTransaction() throws Exception {
    CuratorOp c1 = cf.transactionOp().create().forPath("/" + RandomString.make(4));
    CuratorOp c2 = cf.transactionOp().create().forPath("/dd");
    CuratorOp c3 = cf.transactionOp().delete().forPath("/dd");
    List<CuratorTransactionResult> transList = cf.transaction().forOperations(c1, c2, c3);
    transList.stream().forEach(r -> log.info(" {} {} {}", r.getForPath(), r.getResultPath(), r.getType().name()));

    cf.create().forPath("/" + RandomString.make(4));
    cf.create().forPath("/dd");
    cf.delete().forPath("/dd");
  }

  //curator-recipes
  @Test
  public void testListen() throws Exception {
    NodeCache nc = new NodeCache(cf, "/dd", false);
    nc.start();
    //只能在监听器里读取值
//    log.info("data changed before {}", new String(nc.getCurrentData().getData()));
    CountDownLatch cdl = new CountDownLatch(3);
    nc.getListenable().addListener(() -> {
        log.info("data changed to {}", new String(nc.getCurrentData().getData()));
        cdl.countDown();
    });

    //监听子结点
    PathChildrenCache pcc = new PathChildrenCache(cf, "/hasChild", true);
    pcc.start();
    pcc.getListenable().addListener((CuratorFramework var1, PathChildrenCacheEvent var2) -> {
      log.info("namespace {}", var1.getNamespace());
      log.info(" {} {}", var2.getType().name(), var2.getData());
      cdl.countDown();
    });

    cdl.await();
  }

}
