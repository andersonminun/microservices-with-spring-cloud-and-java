package br.com.anderson.config;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public SwaggerUiConfigParameters swaggerUiConfigParameters(SwaggerUiConfigProperties properties) {
        return new SwaggerUiConfigParameters(properties);
    }

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(
        SwaggerUiConfigParameters configParameters,
        RouteDefinitionLocator routeLocator) {

        List<RouteDefinition> definitions = routeLocator.getRouteDefinitions().collectList().block();
        List<GroupedOpenApi> groups = new ArrayList<>();

        if (definitions != null) {

            definitions.stream()
                .filter(route -> route.getId().matches(".*-service"))
                .forEach(route -> {
                String routeId = route.getId();
                configParameters.addGroup(routeId);
                groups.add(GroupedOpenApi.builder()
                        .group(routeId)
                        .pathsToMatch("/" + routeId + "/**")
                        .build());
            });
        }

        return groups;
    }
}
