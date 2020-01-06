package com.example.logDemo.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClientTest {

  public static void main(String[] args) throws IOException {
    SocketChannel socketChannel = SocketChannel.open();
    socketChannel.connect(new InetSocketAddress("localhost", 9999));
    ByteBuffer buf = ByteBuffer.allocate(48);
    int bytesRead = socketChannel.read(buf);
    System.out.println(new String(buf.array()));
    socketChannel.close();
  }
}
