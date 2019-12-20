package com.example.logDemo.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ApiTest implements Watcher {

//  private static CountDownLatch countDownLatch = new CountDownLatch(1);

  @Override
  public void process(WatchedEvent watchedEvent) {
//    if (watchedEvent.getType() == Watcher.Event.EventType.)
//    if(Event.KeeperState.SyncConnected == watchedEvent.getState())
//      countDownLatch.countDown();
    log.info("~~~~~~ get event {}", watchedEvent.getState().name());
    log.info("~~~~~~ event type {}", watchedEvent.getType().name());
  }

  public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
    ApiTest test = new ApiTest();
    ZooKeeper zk = new ZooKeeper("localhost:2181", 1000 * 600, test);

    //zk.addAuthInfo(); 权限
    log.info("status {}", zk.getState().name());

    zk.create("/test/aa", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    zk.create("/test/bb", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

//    zk.create("/test/cc", "cc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    zk.create("/test/dd", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);

    String path = "/test/async";
    if (zk.exists(path, false) == null) {
      zk.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    zk.create("/test/async/aa", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
            new IStringCallBack(), "test add");

    //watch=true表示添加一次监听,process()才会收到事件NodeChildrenChanged
    List<String> children = zk.getChildren("/test", true);
    children.stream().forEach(s -> log.info("Child {}", s));

    path = "/test/dd0000000071";
    if (zk.exists(path, false) != null) {
//      zk.delete("/test/dd0000000041", -1);
      zk.delete(path, -1, new IVoidCallback(), "test delete");
    }

    path = "/test/cc";
    //传入一个实体对象接受数据
    Stat stat = new Stat();
    String data = new String(zk.getData(path, true, stat));
    //指定要更改的版本数据，版本不对报错
    log.info("{} data is {} {}", stat.getCzxid(), data, stat.getVersion());
    zk.setData(path, "cc updated".getBytes(), stat.getVersion());

//    zk.close();  //: Got ping response for sessionid: 0x100250dd872000d after 1ms
    TimeUnit.SECONDS.sleep(3);
    log.info("finish");
  }
}
