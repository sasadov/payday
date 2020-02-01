package com.paydaydemo.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication(exclude = {RepositoryRestMvcAutoConfiguration.class, SecurityAutoConfiguration.class})
@ComponentScan(basePackages = { "com.paydaydemo.customers.controller", "com.paydaydemo.customers.repository", "com.paydaydemo.customers.service"})
@EntityScan("com.paydaydemo.customers.model")
@EnableJpaRepositories("com.paydaydemo.customers.repository")
@EnableSwagger2WebMvc
@EnableDiscoveryClient
public class CustomerServer {

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
	
	public static void main(String[] args) {

	    System.setProperty("spring.config.name", "application");
	    SpringApplication.run(CustomerServer.class, args);
	}
}