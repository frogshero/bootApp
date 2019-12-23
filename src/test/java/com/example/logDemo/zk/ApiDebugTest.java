package com.example.logDemo.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.io.IOException;

@Slf4j
public class ApiDebugTest implements Watcher {

  public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
    ApiDebugTest watcher = new ApiDebugTest();

    ZooKeeper zk = new ZooKeeper("localhost:2181", 1000 * 600, watcher);

    zk.create("/test/aa11", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

    zk.close();
  }

  @Override
  public void process(WatchedEvent event) {
    log.info("~~~~~~ get event {}", event.getState().name());
    log.info("~~~~~~ event type {}", event.getType().name());
  }
}
