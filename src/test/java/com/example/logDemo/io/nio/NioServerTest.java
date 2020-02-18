package com.example.logDemo.io.nio;

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

      byte[] append = " -> 使用 FileChannel 从Buffer里读取数据的例子, 来自于http://tutorials.jenkov.com/和JDK文档".getBytes();
      byte[] response = new byte[len + append.length];
      System.arraycopy(data, 0, response, 0, len);
      System.arraycopy(append, 0, response, len, append.length);
      bb.put(response);
      bb.flip();  //position = 0
      while (bb.hasRemaining()) {
        socketChannel.write(bb);
      }
      log.info("send response: " + new String(response));
    }
  }
}
