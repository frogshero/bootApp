package com.example.logDemo.net.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

@Slf4j
public class NioServerTest {

  public static void main(String[] args) throws IOException {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.socket().bind(new InetSocketAddress(9999));
    while (true) {
      log.info("listen to ~~~~~~~~~");
      SocketChannel socketChannel = serverSocketChannel.accept();
      ByteBuffer bb = ByteBuffer.allocate(1024);
      int len = socketChannel.read(bb);
      byte[] data = new byte[len];
      bb.flip();
      bb.get(data, 0, len);
      log.info("received String: " + new String(data));

      bb.clear();
      bb.put("def".getBytes()); //position = 3
      bb.flip();  //position = 0
      while (bb.hasRemaining()) {
        socketChannel.write(bb);
      }
      log.info("send response: " + new String(bb.array()));
    }
  }
}
