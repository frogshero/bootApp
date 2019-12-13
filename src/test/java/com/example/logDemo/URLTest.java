package com.example.logDemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class URLTest {

  @Test
  public void test() throws IOException {
    URL url = new URL("file:/D:/wgf/dev/java/testsrc/bootApp/bootApp/target/test-classes/bbb.txt");
    log.info(url.getProtocol());
    log.info(url.getPath());  ///D:/wgf/dev/java/testsrc/bootApp/bootApp/target/test-classes/bbb.txt
    log.info(url.getHost());
    log.info(url.getFile());

    //中介者 还是 外观 模式？封装了可以通过InputStream读取的资源
    try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
      log.info(br.readLine());
    }

    url = new URL("http://localhost:9081/health");
    //HttpURLConnection
    try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))){
      log.info(br.readLine());
    }
  }
}
