package com.example.logDemo.config;

import org.springframework.boot.env.RandomValuePropertySource;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;
import java.util.UUID;


public class AppInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {
    CompositePropertySource cps = new CompositePropertySource("test");
//    RandomValuePropertySource rvps = new RandomValuePropertySource();
    Properties p = new Properties();
    p.setProperty("test.uuid", UUID.randomUUID().toString());
    PropertiesPropertySource pps = new PropertiesPropertySource("test", p);
    cps.addFirstPropertySource(pps);

    applicationContext.getEnvironment().getPropertySources().addFirst(cps);
  }
}
