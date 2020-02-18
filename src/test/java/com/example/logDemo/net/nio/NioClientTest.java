package com.example.logDemo.net.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Set;

@Slf4j
public class NioClientTest {

  //byte[] data = {'a', 'b'};
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

    selector.selectNow();
    if ((selectionKey.readyOps() & SelectionKey.OP_WRITE) != 0) {
//      bb.put("abc".getBytes());
//      bb.flip();  //position = 0 转换为读取内容状态
//      socketChannel.write(bb);
//      log.info(new String(bb.array()));
      fileToBB("bbb.txt", socketChannel);  //从文件直接发送
    } else {
      log.info("cannot write");
    }

    Thread.sleep(1000);  //等服务端发反馈
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      ByteBuffer bb = ByteBuffer.allocate(10);

      int channels = selector.selectNow();
      while (channels > 0) {
        if (selectionKey.isReadable()) {
          bb.clear();
          int len = socketChannel.read(bb);  //unblocking Mode
          if (len > 0) {
            bb.flip();
            baos.write(Arrays.copyOf(bb.array(), len));
//          while (bb.hasRemaining()) {
          } else {
            log.info("No response");
          }
        } else {
          log.info("Cannot read");
        }
        channels = selector.selectNow();   //一次读不完再selectNow
      }
      log.info("get response: {}", new String(baos.toByteArray()));
    }

    socketChannel.close();
  }

  @Test
  public void testFile() {

  }

  private static void fileToBB(String resource, SocketChannel destChannel) throws IOException {
    File file = new ClassPathResource(resource).getFile();
    RandomAccessFile raf = new RandomAccessFile(file, "rw");
    try {
      FileChannel fc = raf.getChannel();

      ByteBuffer bb = ByteBuffer.allocate((int)fc.size());
      fc.read(bb);
      log.info("file content {}", new String(bb.array()));

      bb.flip();
      fc.transferTo(0, fc.size(), destChannel);
    } finally {
      raf.close();
    }
  }
}
