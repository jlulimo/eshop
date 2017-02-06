package com.nnfs.api.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ServletComponentScan
@ImportResource("classpath*:config/shiro/spring-*.xml")
public class App {
	public static void main(String[] args) {
		System.out.println("spring boot start...");
//		SpringApplication.run(args, new String[] { "classpath*:config/shiro/spring-*.xml", });
		SpringApplication.run(App.class);
	}
}
