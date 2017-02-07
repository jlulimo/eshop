package com.nnfs.api.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
@ImportResource("classpath*:config/shiro/spring-*.xml")
@EnableTransactionManagement
public class App {
	public static void main(String[] args) {
		System.out.println("spring boot start...");
		SpringApplication.run(App.class);
	}
}
