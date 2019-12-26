package com.example.logDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@Slf4j
public class TestController {
  @Value("${testVal}")
  private String testVal;

  @Value("${test.uuid}")
  private String testVal2;

  @GetMapping("/test/{num}")
  public User test(@PathVariable int num) {
    //返回值不能是String，否则StringHttpMessageConverter会认为能处理它，导致在@RestControllerAdvice里转为ApiResult后StringHttpMessageConverter处理不了
    /**
     * RequestContextListener实现了tomcat的ServletRequestListener接口。
     * Servlet listener that exposes the request to the current thread, through both LocaleContextHolder
     * and RequestContextHolder. To be registered as listener in web.xml.
     */
    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    attr.setAttribute("attr", attr.getAttribute("attr", RequestAttributes.SCOPE_SESSION) + "a", RequestAttributes.SCOPE_SESSION);
    User u = new User();
    u.setName(testVal + num + " " + testVal2);
    return u;
  }
}
