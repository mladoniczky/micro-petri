package com.mladoniczky.micropetri.gateway.routes;

import com.mladoniczky.micropetri.gateway.configuration.GatewayConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceRouteConfiguration {

    private final GatewayConfigurationProperties configurationProperties;

    @Autowired
    public ServiceRouteConfiguration(GatewayConfigurationProperties configurationProperties) {
        this.configurationProperties = configurationProperties;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("net-service", r -> r.path("/api/nets/**")
                        .filters(f ->
                                f.stripPrefix(1)
                        )
                        .uri(configurationProperties.getNetService()))
                .build();
    }


}
