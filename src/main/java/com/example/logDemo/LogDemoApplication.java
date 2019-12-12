package com.example.logDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@RestController
public class LogDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogDemoApplication.class, args);
	}
	
	@GetMapping("/test")
	public void test() {
		log.info("aaaa");
	}

}
