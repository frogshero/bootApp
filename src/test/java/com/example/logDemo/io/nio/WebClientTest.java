package com.example.logDemo.io.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

@Slf4j
public class WebClientTest {

  private void sendGet(String req, ByteBuffer bb, SocketChannel channel) throws IOException {
    bb.clear();
    bb.put(req.getBytes(StandardCharsets.UTF_8));
    bb.flip();  //必须flip，从position=0的位置开始写
    channel.write(bb);
    log.info("write completed");
  }

  private int getResp(ByteBuffer bb, SocketChannel channel) throws IOException {
    int cnt = 0;
    bb.clear(); //必须要clear，否则读不到 ！！！
    cnt = channel.read(bb);
    if (cnt > 0) {
      log.info("content len {}: {}", cnt, new String(bb.array()));
    } else {
      log.error("Not content");
    }
    return cnt;
  }

  @Test
  public void test() throws IOException {
//    String HOST = "localhost";
//    int PORT = 8081;
//    String URL = "/health";

    String HOST = "www.baidu.com";
    int PORT = 80;
    String URL = "/";

    SocketChannel channel = SocketChannel.open();
    channel.connect(new InetSocketAddress(HOST, PORT));

    ByteBuffer bb = ByteBuffer.allocate(1024 * 1024);
    String req = String.format("GET %s HTTP/1.1\r\n" +
            "Host: %s\r\n" +
            "Connection: close\r\n" +
            "Accept: */*\r\n" +
            "\r\n", URL, HOST + ":" + PORT);

    sendGet(req, bb, channel);
    int dataLen = getResp(bb, channel);

    //sendGet(req, bb, channel);
    int c = 1;
    while (dataLen > 0) {
      dataLen = getResp(bb, channel);
      c++;
    }
    log.info("total data {}", c);

    channel.close();
  }
}
