package com.javastaff.bddfun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@OpenAPIDefinition
public class BDDFunApp{

	public static void main(String[] args) {
		SpringApplication.run(BDDFunApp.class, args);
	}
	
    /**
     * Custom open API.
     *
     * @param appDesciption the app desciption
     * @param appTitle the app title
     * @param version the version
     * @return the open API
     */
    @Bean
    public OpenAPI customOpenAPI(@Value("${application.description}") String appDesciption, 
    							 @Value("${application.title}") String appTitle) {
    	return new OpenAPI()
		          .info(new Info()
        		  .title(appTitle)
        		  .description(appDesciption));
    }
}

