package com.example.logDemo.design;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

@Slf4j
public class ProxyTest implements InvocationHandler {

  ProxiedClass proxiedClass = new ProxiedClass();
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    log.info("in proxy");
    if (method.getName().equals("doSome")) {
      return proxiedClass.doSome();
    }
    else {
      return proxiedClass.doOther();
    }
  }

  public interface ProxyInterface {
    Date doSome();
    String doOther();
  }

  class ProxiedClass implements ProxyInterface {
    @Override
    public Date doSome() {
        log.info("done");
        return new Date();
    }

    @Override
    public String doOther() {
      log.info("do other");
      return "other";
    }
  }

  @Test
  public void test() {
    ProxyInterface p = (ProxyInterface)Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{ProxyInterface.class}, this);
    p.doSome();
    p.doOther();
  }
}
