package com.example.logDemo.net;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Slf4j
public class URL_URI_Test {

  /**
   * 在Java的URI中，一个URI实例可以代表绝对的，也可以是相对的，只要它符合URI的语法规则。而URL类则不仅符合语义，还包含了定位该资源的信息，
   * 因此它不能是相对的。在Java类库中，URI类不包含任何访问资源的方法，它唯一的作用就是解析。相反的是，URL类可以打开一个到达资源的流。
   */
  @Test
  public void test() throws IOException, URISyntaxException {
    URL url = new URL("file:/D:/wgf/dev/java/testsrc/bootApp/bootApp/target/test-classes/bbb.txt");
    log.info(url.getProtocol());
    log.info(url.getPath());  ///D:/wgf/dev/java/testsrc/bootApp/bootApp/target/test-classes/bbb.txt
    log.info(url.getHost());
    log.info(url.getFile());
    URI uri = url.toURI();
    log.info("URI:{}", uri.toString());
    log.info("Relative {}", new URI("file:/D:/wgf/dev/java/testsrc/bootApp").relativize(uri).toString());

    //中介者 还是 外观 模式？封装了可以通过InputStream读取的资源
    try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
      log.info(br.readLine());
    }

    //url.toURI().relativize() //url没有relativize
    //url.toURI() //没有openStream

    URL url2 = new URL("http://sports.sina.com.cn/");
    log.info("URI:{}", url2.toURI().toString());
//    url = new URL("http://localhost:9081/health");
//    //HttpURLConnection
//    try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))){
//      log.info(br.readLine());
//    }
  }
}
