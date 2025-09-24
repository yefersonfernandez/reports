package com.powerup.emailses.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesAsyncClient;

@Configuration
public class SesConfig {

    private final SesProperties sesProperties;

    public SesConfig(SesProperties sesProperties) {
        this.sesProperties = sesProperties;
    }

    @Bean
    public SesAsyncClient sesClient() {
        return SesAsyncClient.builder()
                .region(Region.of(sesProperties.region()))
                .credentialsProvider(DefaultCredentialsProvider.builder().build())
                .build();
    }
}
