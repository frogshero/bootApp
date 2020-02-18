package com.example.logDemo.dataType;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;

@Slf4j
public class IntegerTest {
  private static final int COUNT_BITS = Integer.SIZE - 3;  //32 -3
  private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
  private static final int RUNNING    = -1 << COUNT_BITS;
  private static final int SHUTDOWN   =  0 << COUNT_BITS;
  private static final int STOP       =  1 << COUNT_BITS;
  private static final int TIDYING    =  2 << COUNT_BITS;
  private static final int TERMINATED =  3 << COUNT_BITS;

  @Test
  public void testType() {
    log.info("{}", Integer.toBinaryString(13));
    int COUNT_BITS = Integer.SIZE - 3;
    log.info("Integer bit SIZE {}", COUNT_BITS);
    log.info("1 {}", Integer.toBinaryString(1));
    log.info("CAPACITY {}", CAPACITY);
    log.info("Integer MAX_VALUE {}", Integer.MAX_VALUE);
    log.info("CAPACITY {}", Integer.toBinaryString(CAPACITY)); //左移，右边补0
    log.info("~CAPACITY {}", Integer.toBinaryString(~CAPACITY)); //取反
    log.info("-1 {}", Integer.toBinaryString(-1));
    log.info("RUNNING {}", Integer.toBinaryString(RUNNING));
//    log.info(clz.getResource("IntegerTest.class").toString()); //包下的资源文件可能不会到target目录
//    log.info(clz.getResource("IntegerTest.class").toURI().toString());
    //存储在高位
    log.info("SHUTDOWN {}", Integer.toBinaryString(SHUTDOWN));
    log.info("STOP {}", Integer.toBinaryString(STOP));
    log.info("TIDYING {}", Integer.toBinaryString(TIDYING));
    log.info("TERMINATED {}", Integer.toBinaryString(TERMINATED));
  }

  @Test
  public void testInt() {
    int DEFAULT_LENGTH = 8;
    char[] SYMBOL;
    int KEY_BITS;
    Random random;
    int length;

    StringBuilder symbol = new StringBuilder();

    char character;
    for(character = '0'; character <= '9'; ++character) {
      symbol.append(character);
    }
    for(character = 'a'; character <= 'z'; ++character) {
      symbol.append(character);
    }
    for(character = 'A'; character <= 'Z'; ++character) {
      symbol.append(character);
    }

    SYMBOL = symbol.toString().toCharArray();
    int len = 65; //SYMBOL.length;
    log.info("SYMBOL len: {} {}", len, Integer.toBinaryString(len));
    //为1的bit位数量
    log.info("bitCount: {}", len);
    //前导0
    log.info("numberOfLeadingZeros {}", Integer.numberOfLeadingZeros(len));
    int bits = 32 - Integer.numberOfLeadingZeros(len);
    //有效位
    log.info("bits: {}", bits);
    //Integer.bitCount 是1的有多少位
    KEY_BITS = bits - (Integer.bitCount(len) == bits ? 0 : 1);
    log.info("KEY_BITS: {}", KEY_BITS);
  }
}
