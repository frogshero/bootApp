package com.example.logDemo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LogDemoApplicationTests {

  @Test
	public void contextLoads() throws Exception {
    Class clz = this.getClass();
    log.info(clz.getSimpleName());
    log.info(clz.getCanonicalName());
    log.info("isEnum {} ", clz.isEnum());
    log.info("getEnumConstants {} ", clz.getEnumConstants());
    log.info("isAnnotation {} ", clz.isAnnotation());
    log.info("isAnnotationPresent {}", clz.isAnnotationPresent(Component.class));
    log.info("getAnnotations.length {}", clz.getAnnotations().length);
    log.info(clz.getPackage().getName());
    log.info(clz.getResource("aaa.txt").toString());
    log.info(clz.getResource("aaa.txt").toURI().toString());
    log.info(clz.getResource("/bbb.txt").toString());
    //clz.newInstance()
    //clz.getConstructor(Void.class).newInstance();
//    clz.forName(className)
	}

}
