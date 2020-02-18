package com.example.logDemo;


import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.net.URL;

@Slf4j
@Component
public class ClassTest {

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

    log.info(clz.getResource("/bbb.txt").toString());
    //clz.newInstance()
    //clz.getConstructor(Void.class).newInstance();
//    clz.forName(className)

	}

}
