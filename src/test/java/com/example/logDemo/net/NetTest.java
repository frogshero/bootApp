package com.example.logDemo.net;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class NetTest {

  @Test
  public void test() throws UnknownHostException {
    log.info("loopback {}", InetAddress.getLoopbackAddress().getHostAddress());
    log.info("hostname {}", InetAddress.getLocalHost().getCanonicalHostName());
  }
}
