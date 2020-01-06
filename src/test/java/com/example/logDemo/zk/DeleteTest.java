package com.example.logDemo.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class DeleteTest {

  @Test
  public void test() throws InterruptedException, KeeperException, IOException {
    ZooKeeper zk = new ZooKeeper("localhost:2181", 1000 * 10, null);
    Stat stat = zk.exists("/test/cc", false);
    log.info("version: {}", stat.getVersion());
    zk.delete("/test/cc", stat.getVersion());
    //BadVersion for /test/cc
    //NoNode for /test/dd
    //Directory not empty for /test/cc
    zk.close();
  }
}
