package com.example.logDemo.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import java.io.IOException;

@Slf4j
public class WatchAsynTest implements Watcher, AsyncCallback.StatCallback {

  ZooKeeper zk;
  private String nodePath;

  public WatchAsynTest() throws IOException, KeeperException, InterruptedException {
    zk = new ZooKeeper("localhost:2181", 1000 * 600, this);
    nodePath = zk.create("/test/aa/ephemeral", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

  }

  @Test
  public void test() throws InterruptedException {
    log.info("-----------" + nodePath);
    //exists的异步版本，用AsyncCallback.StatCallback接受异步结果
    zk.exists(nodePath, true, this, null);

    Thread.sleep(1000 * 600);
    zk.close();
  }

  @Override
  public void process(WatchedEvent event) {
    log.info("process {}", event.toString());
    zk.exists(nodePath, true, this, null);

  }

  @Override
  public void processResult(int rc, String path, Object ctx, Stat stat) {
    //返回值也是event，在EventThread.processEvent
    log.info("processResult {} {}", path, stat);
  }
}
