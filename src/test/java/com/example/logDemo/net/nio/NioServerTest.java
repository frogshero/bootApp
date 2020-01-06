package com.example.logDemo.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioServerTest {

  public static void main(String[] args) throws IOException {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.socket().bind(new InetSocketAddress(9999));
    while (true) {
      System.out.println("listen to ~~~~~~~~~");
      SocketChannel socketChannel = serverSocketChannel.accept();
      ByteBuffer buf = ByteBuffer.allocate(48);
      buf.putChar('h').putChar('u').putChar('b');
      socketChannel.write(buf);
      System.out.println("listened ~~~~~~~~~~");
    }
  }
}
