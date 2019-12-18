package com.example.logDemo.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

@Slf4j
public class IStringCallBack extends Stat implements AsyncCallback.StringCallback {

  @Override
  public void processResult(int rc, String path, Object ctx, String name) {
    //rc: result code 失败原因
    log.info("processResult {} {} {} {}", rc, path, ctx, name);
  }
}
