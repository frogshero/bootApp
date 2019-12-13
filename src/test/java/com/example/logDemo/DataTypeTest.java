package com.example.logDemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class DataTypeTest {
  private static final int COUNT_BITS = Integer.SIZE - 3;  //32 -3
  private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
  private static final int RUNNING    = -1 << COUNT_BITS;
  private static final int SHUTDOWN   =  0 << COUNT_BITS;
  private static final int STOP       =  1 << COUNT_BITS;
  private static final int TIDYING    =  2 << COUNT_BITS;
  private static final int TERMINATED =  3 << COUNT_BITS;

  @Test
  public void testType() {
    int COUNT_BITS = Integer.SIZE - 3;
    log.info("Integer bit SIZE {}", COUNT_BITS);
    log.info("1 {}", Integer.toBinaryString(1));
    log.info("CAPACITY {}", CAPACITY);
    log.info("Integer MAX_VALUE {}", Integer.MAX_VALUE);
    log.info("CAPACITY {}", Integer.toBinaryString(CAPACITY)); //左移，右边补0
    log.info("-1 {}", Integer.toBinaryString(-1));
    log.info("RUNNING {}", Integer.toBinaryString(RUNNING));

    //存储在高位
    log.info("SHUTDOWN {}", Integer.toBinaryString(SHUTDOWN));
    log.info("STOP {}", Integer.toBinaryString(STOP));
    log.info("TIDYING {}", Integer.toBinaryString(TIDYING));
    log.info("TERMINATED {}", Integer.toBinaryString(TERMINATED));
  }
}
