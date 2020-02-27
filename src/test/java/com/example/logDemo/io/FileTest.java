package com.example.logDemo.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class FileTest {

  @Test
  public void test() throws IOException {
//    List<String> lines = Files.readAllLines(Paths.get(new ClassPathResource("bbb.txt").getURI()));
//    log.info("path: {}", Paths.get(new ClassPathResource("bbb.txt").getURI()).getFileName());
    List<String> lines = Files.readAllLines(Paths.get("d:/files.txt"));
    log.info("lines: {}", lines);
  }
}
