package com.mladoniczky.micropetri.gateway.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "micro-petri")
public class GatewayConfigurationProperties {

    private String netService;


}
