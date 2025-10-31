package org.al.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfig {

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Value("${patient.service.url}")
    private String patientServiceUrl;

    @Value("${form.service.url}")
    private String formServiceUrl;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/auth/**")
                        .uri(userServiceUrl))
                .route("patient-service", r -> r.path("/patients/**")
                        .uri(patientServiceUrl))
                .route("form-service", r -> r.path("/forms/**")
                        .uri(formServiceUrl))
                .build();
    }
}