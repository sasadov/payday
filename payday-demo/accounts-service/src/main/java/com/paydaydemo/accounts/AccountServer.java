package com.paydaydemo.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.paydaydemo.authentication.AuthenticationServer;
import com.paydaydemo.security.SecurityConfig;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
@ComponentScan(basePackages = {"com.paydaydemo.accounts.controller", "com.paydaydemo.accounts.repository", "com.paydaydemo.accounts.service" , "com.paydaydemo.security"})
@EntityScan("com.paydaydemo.accounts.model")
@EnableJpaRepositories("com.paydaydemo.accounts.repository")
@EnableSwagger2WebMvc
@Import({AuthenticationServer.class, SecurityConfig.class})
public class AccountServer {
	
	public static void main(String[] args) {
	    System.setProperty("spring.config.name", "application");
	    SpringApplication.run(AccountServer.class, args);
	}
}