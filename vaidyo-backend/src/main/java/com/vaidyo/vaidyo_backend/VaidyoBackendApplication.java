package com.vaidyo.vaidyo_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VaidyoBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(
				VaidyoBackendApplication.class, args);
	}
}