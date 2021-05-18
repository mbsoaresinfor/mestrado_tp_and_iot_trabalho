package com.mbs.tp_and_iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.mbs.tp_and_iot.gateway")
@SpringBootApplication
public class StartApplication {

	// classe para inciar aplicacao.
	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

	
}
