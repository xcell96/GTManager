package com.xcell.GTManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GTManagerApplication {

	static void main(String[] args) {
		SpringApplication.run(GTManagerApplication.class, args);
	}

}
