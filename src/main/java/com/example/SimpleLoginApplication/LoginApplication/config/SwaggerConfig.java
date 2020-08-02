package com.example.SimpleLoginApplication.LoginApplication.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(regex("/.*")).build();
	}

	public ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo("My Rest API", "some description of the api", "2.8", "",
				new Contact("Vaishnavi Neema", "", "vaishnavi.neema09@gmail.com"), "", "", null);
		return apiInfo;
	}
}