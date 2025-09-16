package com.powerup.api.config;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalExceptionConfig {

    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }

}
