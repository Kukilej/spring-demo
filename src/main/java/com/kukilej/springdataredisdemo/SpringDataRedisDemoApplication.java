package com.kukilej.springdataredisdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringDataRedisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRedisDemoApplication.class, args);
	}

}
