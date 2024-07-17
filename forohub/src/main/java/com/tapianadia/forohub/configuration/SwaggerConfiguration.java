package com.tapianadia.forohub.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "FORO HUB",
                description = "En este foro, se puede manejar tanto perfiles como usuarios, categorias, etc.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Nadia Tapia"),
                license = @License(
                        name = "Standard Software Use License for ProgramadoresUnidos",
                        url = "www.programadoresunidos.com/licence"
                )
        ),
        security = @SecurityRequirement(
                name = "Security Token"
        )
)

@SecurityScheme(
        name = "Security Token",
        description = "Access Token For My API",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)

public class SwaggerConfiguration {
}
