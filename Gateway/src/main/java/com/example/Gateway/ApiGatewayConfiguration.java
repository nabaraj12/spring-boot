package com.example.Gateway;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/library/**")
                        .uri("http://localhost:8082/"))
                .route(p -> p.path("/users/**")
                        .uri("lb://users-service"))
                .route(p -> p.path("/books/**")
                        .uri("lb://books-service"))
                .route(p -> p.path("/port/**")
                        .uri("lb://books-service"))

                .build();
    }
}
