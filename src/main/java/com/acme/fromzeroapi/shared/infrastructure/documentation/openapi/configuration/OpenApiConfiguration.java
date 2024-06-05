package com.acme.fromzeroapi.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI fromZeroApiOpenApi() {
        // General configuration
        var openAPI = new OpenAPI();
        openAPI.info(new Info()
                        .title("From Zero Web Application API")
                        .description("From Zero Web Application API documentation.")
                        .version("v1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("From Zero Api wiki Documentation")
                        .url("https://from-zero-api.wiki.github.io/docs"));
        return openAPI;
    }
}
