package com.learn.clinic.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI insightBlogApi() {
        return new OpenAPI().info(new Info()
                .title("Clinic API")
                .description("Spring Boot Application")
                .contact(new Contact().name("Fly"))
                .version("1.0.0 GA")
                .termsOfService(""));
    }

    @Bean
    @SuppressWarnings("all")
    public GroupedOpenApi defaultApiGroup() {
        return GroupedOpenApi.builder()
                .packagesToScan("com.learn.clinic.controller")
                .group("default")
                .pathsToMatch("/**")
                .build();
    }

}