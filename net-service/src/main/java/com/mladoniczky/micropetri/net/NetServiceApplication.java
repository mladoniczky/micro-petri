package com.mladoniczky.micropetri.net;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


@SpringBootApplication
@EnableConfigurationProperties
@EnableMongoAuditing
public class NetServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetServiceApplication.class, args);
    }

}
