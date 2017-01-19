package com.nnfs.api.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class App {
	public static void main(String[] args) {
		System.out.println("spring boot start...");
		SpringApplication.run(args, new String[] { "classpath*:config/shiro/spring-*.xml", });
	}
}
