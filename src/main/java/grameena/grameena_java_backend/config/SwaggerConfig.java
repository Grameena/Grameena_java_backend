package grameena.grameena_java_backend.config;

import io.swagger.v3.oas.models.security.SecurityRequirement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
    @Configuration
    @OpenAPIDefinition(
            info = @Info(
                    title = "Grameena API",
                    version = "1.0",
                    description = "Grameena Backend APIs"
            )
    )
    @SecurityScheme(
            name = "Bearer Authentication",
            type = SecuritySchemeType.HTTP,
            scheme = "bearer",
            bearerFormat = "JWT"
    )
    public class SwaggerConfig {
    }

