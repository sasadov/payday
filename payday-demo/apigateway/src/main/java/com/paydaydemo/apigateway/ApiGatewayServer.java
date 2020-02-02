package com.paydaydemo.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
@ComponentScan(basePackages = { "com.paydaydemo.apigateway.controller", "com.paydaydemo.apigateway.repository", "com.paydaydemo.apigateway.service" , "com.paydaydemo.apigateway.security"})
@EntityScan("com.paydaydemo.apigateway.model")
@EnableJpaRepositories("com.paydaydemo.apigateway.repository")
@EnableSwagger2WebMvc
public class ApiGatewayServer {
	
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
	    SpringApplication.run(ApiGatewayServer.class, args);
	}
	
	@LoadBalanced   
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
