package com.powerup.emailses.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.ses")
public record SesProperties(
        String region,
        String sourceEmail,
        String destinationEmail
) {}