package com.sgbd.sgbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
public class SgbdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgbdApplication.class, args);
	}

}

