package org.al.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfig {

    @Value("${user.service.url:http://localhost:8080}")
    private String userServiceUrl;

    @Value("${patient.service.url:http://localhost:8081}")
    private String patientServiceUrl;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/auth/**")
                        .uri(userServiceUrl))
                .route("patient-service", r -> r.path("/patients/**")
                        .uri(patientServiceUrl))
                .build();
    }
}