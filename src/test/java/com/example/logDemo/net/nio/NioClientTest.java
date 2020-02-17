package com.example.logDemo.net.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

@Slf4j
public class NioClientTest {

  public static void main(String[] args) throws IOException, InterruptedException {
    Selector selector = Selector.open();

    SocketChannel socketChannel = SocketChannel.open();
    socketChannel.configureBlocking(false);  //用Selector就必须是异步方式

    //socketChannel注册到selector的时候要指定是否需要读写
    SelectionKey selectionKey = socketChannel.register(selector,
            SelectionKey.OP_CONNECT | SelectionKey.OP_WRITE | SelectionKey.OP_READ);

    socketChannel.connect(new InetSocketAddress("localhost", 9999));

    selector.selectNow(); //异步，同步用selector.select()
    //Set<SelectionKey> keys = selector.selectedKeys(); //如果一个线程要管理多个Channel
    if (selectionKey.isConnectable()) {
      boolean connected = socketChannel.finishConnect();
      log.info("client connected to server {}", connected);
    }

    ByteBuffer bb = ByteBuffer.allocate(3);

    selector.selectNow();
    if ((selectionKey.readyOps() & SelectionKey.OP_WRITE) != 0) {
      bb.put("abc".getBytes());
      bb.flip();  //position = 0 转换为读取内容状态
      socketChannel.write(bb);
//    int bytesRead = socketChannel.read(buf);
      log.info(new String(bb.array()));
    } else {
      log.info("cannot write");
    }

    Thread.sleep(1000);  //等服务端发反馈
    selector.selectNow();
    if (selectionKey.isReadable()) {
      bb.clear();
      int len = socketChannel.read(bb);  //blocking Mode
      if (len > 0) {
        log.info("get response: {}", new String(bb.array()));
      } else {
        log.info("No response");
      }
    } else {
      log.info("Cannot read");
    }

    socketChannel.close();
  }
}
