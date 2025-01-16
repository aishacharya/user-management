package com.example.user_management.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Swagger config.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Custom open api open api.
     *
     * @return the open api
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("User management API")
                        .description("Details the APIs to manage User")
                        .version("1.0"))
                .addSecurityItem(new SecurityRequirement().addList("Enter user credentials"))
                .components(new Components().addSecuritySchemes("Enter user credentials", new SecurityScheme()
                        .name("Enter user credentials").type(SecurityScheme.Type.HTTP).scheme("basic")));
    }
}
