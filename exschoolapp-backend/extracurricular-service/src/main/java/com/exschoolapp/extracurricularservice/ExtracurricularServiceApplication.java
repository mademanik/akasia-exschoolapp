package com.exschoolapp.extracurricularservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ExtracurricularServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtracurricularServiceApplication.class, args);
	}

}
