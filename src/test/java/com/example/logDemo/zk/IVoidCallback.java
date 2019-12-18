package com.example.logDemo.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.AsyncCallback;

@Slf4j
public class IVoidCallback implements AsyncCallback.VoidCallback {
  @Override
  public void processResult(int rc, String path, Object ctx) {
    log.info("delete {} {} {}", rc, path, ctx);
  }
}
