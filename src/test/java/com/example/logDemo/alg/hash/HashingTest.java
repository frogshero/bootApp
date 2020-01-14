package com.example.logDemo.alg.hash;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HashingTest {
  @Test
  public void test() throws InterruptedException {
    HashFunction hf = Hashing.goodFastHash(2);

    HashCode hc = hf.hashString("你好", Charset.forName("UTF-8"));
    log.info("hello {}", hc.asInt());
    TimeUnit.SECONDS.sleep(10);
    HashCode hc2 = hf.hashString("你好", Charset.forName("UTF-8"));
    log.info("hello {}", hc2.asInt());

    hc = hf.hashString("是否发生", Charset.forName("UTF-8"));
    log.info("hello {}", hc.asInt());
    TimeUnit.SECONDS.sleep(10);
    hc2 = hf.hashString("是否发生", Charset.forName("UTF-8"));
    log.info("hello {}", hc2.asInt());
  }
}
