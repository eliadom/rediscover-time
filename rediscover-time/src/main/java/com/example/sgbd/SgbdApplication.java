package com.example.sgbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = {"configs","com.example.sgbd.entities"})
@EnableJpaRepositories
@EnableWebMvc
public class SgbdApplication {



	public static void main(String[] args) {
		SpringApplication.run(SgbdApplication.class, args);
	}

}
