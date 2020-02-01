package com.paydaydemo.transactions;

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
@ComponentScan(basePackages = {"com.paydaydemo.transactions.controller", "com.paydaydemo.transactions.repository", "com.paydaydemo.transactions.service" , "com.paydaydemo.security"})
@EntityScan("com.paydaydemo.transactions.model")
@EnableJpaRepositories("com.paydaydemo.transactions.repository")
@EnableSwagger2WebMvc
@Import({AuthenticationServer.class, SecurityConfig.class})
public class TransactionServer {
	
	public static void main(String[] args) {
	    System.setProperty("spring.config.name", "application");
	    SpringApplication.run(TransactionServer.class, args);
	}
}