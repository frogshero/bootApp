package com.example.logDemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

@Slf4j
public class ReflectTest {

  @Test
  public void test() {
    PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(User.class, "age");
    Method method = pd.getWriteMethod();
    log.info("setMethod: {}", method.getName());
  }
}
