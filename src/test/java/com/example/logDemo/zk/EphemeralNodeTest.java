package com.example.logDemo.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class EphemeralNodeTest implements Watcher {

  @Test
  public void testExists() throws Exception {

    ZooKeeper zk = new ZooKeeper("localhost:2181", 1000 * 10, this);
    String created = zk.create("/test/aa/ephemeral", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    log.info("-----------" + created);  ///test/aa/ephemeral0000000008

//    created = zk.create(created + "/child1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
//    log.info(created);  //NoChildrenForEphemerals  不能在临时结点下加子结点

    if (1==1) {
      throw new Exception("xx");
      //不执行zk.close是不会立刻删除临时结点的；
      //[SessionTracker:ZooKeeperServer@398] - Expiring session 0x10078e11ddb0015, timeout of 400000ms exceeded
      //ClientCnxnSocketNIO.readConnectResult里读取服务端发来的timeout值和sessionId值
      //服务端ZooKeeperServer确定最小sessionTimeout是在setMinSessionTimeout里：
      //this.minSessionTimeout = min == -1 ? tickTime * 2 : min; // tickTime是zoo.cfg配置的
      //tickTime: the basic time unit in milliseconds used by ZooKeeper. It is used to do heartbeats and the minimum session timeout will be twice the tickTime.
    }
    zk.close();
  }

  private CountDownLatch c = new CountDownLatch(1);
  @Test
  public void testExist() throws InterruptedException, IOException, KeeperException {
    ZooKeeper zk = new ZooKeeper("localhost:2181", 1000 * 10, this); //this作为defaultWatcher
    zk.exists("/test/aa/ephemeral0000000034", true);  //使用defaultWatcher监听
    //看看多久自动删除
    c.await();
    zk.close();
  }

  @Test
  public void testOrder() throws IOException, InterruptedException {
    ZooKeeper zk = new ZooKeeper("localhost:2181", 1000 * 600, null);

    CountDownLatch cdl = new CountDownLatch(10);
    for (int i=0; i<10; i++) {
      new Thread(() -> {
        try {
          zk.create("/test/aa/ephemeral", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (KeeperException e) {
          e.printStackTrace();
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          cdl.countDown();
        }
      }).start();
    }

    //结点是递增顺序的
    cdl.await();
    zk.close();

  }


  @Override
  public void process(WatchedEvent event) {
    log.info("+++++++++++" + event.toString());
    if (event.getType().equals(Event.EventType.NodeDeleted)) {
      c.countDown();
    }
  }
}
