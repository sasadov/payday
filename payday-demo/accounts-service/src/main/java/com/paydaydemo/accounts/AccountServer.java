package com.paydaydemo.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication(exclude = {RepositoryRestMvcAutoConfiguration.class, SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.paydaydemo.accounts.controller", "com.paydaydemo.accounts.repository", "com.paydaydemo.accounts.service"})
@EntityScan("com.paydaydemo.accounts.model")
@EnableJpaRepositories("com.paydaydemo.accounts.repository")
@EnableSwagger2WebMvc
@EnableDiscoveryClient
public class AccountServer {
	
	public static void main(String[] args) {
	    System.setProperty("spring.config.name", "application");
	    SpringApplication.run(AccountServer.class, args);
	}
}