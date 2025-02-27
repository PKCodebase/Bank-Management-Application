package com.bank.management.system.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfoConfig {

    @Bean
    public InfoContributor customInfoContributor() {
        return (Info.Builder builder) -> builder
                                .withDetail("name", "Banking Application")
                                .withDetail("version", "1.0.0")
                                .withDetail("description", "Spring Boot Banking Application with Actuator");
    }
}
