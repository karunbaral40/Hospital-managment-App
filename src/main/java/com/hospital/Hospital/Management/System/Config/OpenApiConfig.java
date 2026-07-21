package com.hospital.Hospital.Management.System.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()

                // API metadata
                .info(new Info()
                        .title("Hospital Management System API")
                        .description("REST API for managing doctors and patients. " +
                                "Login first at /auth/login to get a JWT token, " +
                                "then click Authorize and paste the token.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Karun")
                                .email("karun@hospital.com")))

                // JWT security scheme — adds Authorize button to Swagger UI
                .addSecurityItem(new SecurityRequirement()
                        .addList("Bearer Authentication"))

                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Enter your JWT token here. " +
                                                "Get it from POST /auth/login")));
    }
}