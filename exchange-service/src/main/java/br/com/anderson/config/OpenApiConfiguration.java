package br.com.anderson.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition(info = 
    @Info(
        title = "Exchange Service API",
        version = "1.0",
        description = "API para gerenciamento de câmbio"
    )
)
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Exchange Service API")
                        .version("1.0")
                        .description("API para gerenciamento de câmbio")
                        .license(
                            new License()
                            .name("Apache 2.0")
                            .url("")));
    }
}
