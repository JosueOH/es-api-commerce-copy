package com.es.api;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ApiApplication {

	@Value("${application.timezone:UTC}")
	private String applicationTimeZone;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@PostConstruct
	public void executeAfterMain() {
		TimeZone.setDefault(TimeZone.getTimeZone(applicationTimeZone));
	}

}
