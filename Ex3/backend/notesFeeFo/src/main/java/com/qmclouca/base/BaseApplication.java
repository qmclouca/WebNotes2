package com.qmclouca.base;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {
		"com.qmclouca.base.models",
		"com.qmclouca.base.repositories",
		"com.qmclouca.base.controllers",
		"com.qmclouca.base.services.Implementations",
		"com.qmclouca.base.repositories",
		"com.qmclouca.base.repositories.Implementations",
		"com.qmclouca.base.configs",
		"com.qmclouca.base.configs.Auth",
})
public class BaseApplication {
	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}
}
