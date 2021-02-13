package com.mladoniczky.micropetri.gateway.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Component
public class ActiveApplicationPropertiesLogger {

    @EventListener
    public void handleContextRefreshed(ContextRefreshedEvent event) {
        printActiveProperties((ConfigurableEnvironment) event.getApplicationContext().getEnvironment());
    }

    private void printActiveProperties(ConfigurableEnvironment env) {

        System.out.println("************************* ACTIVE APP PROPERTIES ******************************");

        env.getPropertySources().stream()
                .filter(source -> source instanceof MapPropertySource)
                .map(propertySource -> ((MapPropertySource) propertySource).getSource().keySet())
                .flatMap(Collection::stream)
                .distinct()
                .sorted()
                .forEach(key -> {
                    try {
                        log.info(key + "=" + env.getProperty(key));
                    } catch (Exception e) {
                        log.warn("{} -> {}", key, e.getMessage());
                    }
                });
        System.out.println("******************************************************************************");
    }

}
